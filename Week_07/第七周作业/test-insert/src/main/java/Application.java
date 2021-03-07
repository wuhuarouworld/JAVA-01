import java.sql.*;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        //使用事务和addBatch方法，耗时在23-25s
        //method1();
        //使用HikaciCP连接池,耗时在5s左右
        long begin = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                method2();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时： " + (end - begin) + " ms");
    }

    private static void method1() {
        long begin = System.currentTimeMillis();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO t_user (id,nickname,password,gender,birthday,mobile,create_time,update_time) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            //获取数据库连接
            String url = "jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true";
            try {
                connection = DriverManager.getConnection(url, "root", "0904");
                preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 10000; j++) {
                        //用户ID
                        preparedStatement.setObject(1, (j + 1 + i * 10000));
                        //昵称
                        preparedStatement.setObject(2, "jack");
                        //密码
                        preparedStatement.setObject(3, "0904");
                        //性别
                        preparedStatement.setObject(4, "男");
                        //生日
                        preparedStatement.setObject(5, new Date());
                        //手机号
                        preparedStatement.setObject(6, "18357697099");
                        //创建时间
                        preparedStatement.setObject(7, new Date());
                        //最后一次更新时间
                        preparedStatement.setObject(8, new Date());
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
                    //手动提交事务
                    connection.commit();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //关闭自动提交事务
            connection.setAutoCommit(false);
            //手动提交事务
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 " + (end - begin) + " ms");
    }

    private static void method2() {
        PreparedStatement preparedStatement = null;
        String prefix = "INSERT INTO t_user (nickname,password,gender,mobile) VALUES";
        String sql = prefix + " (?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        try {
            preparedStatement = conn.prepareStatement(sql);
            final StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < 50000; j++) {
                buffer.append("(1,1,1,1),");
            }
            String sqlTemp = prefix + buffer.substring(0, buffer.length() - 1);
            preparedStatement.addBatch(sqlTemp);
            preparedStatement.executeBatch();
            //手动提交事务
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != conn) {
                DBUtil.close(conn);
            }
        }
    }
}

