import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true";
    private static final String USER = "root";
    private static final String PASSWORD = "0904";
    private static final long CONN_TIMEOUT = 60 * 1000;
    private static final long IDLE_TIMEOUT = 10 * 60 * 1000;
    private static final int MAXSUM_POOL_SIZE = 100;

    private static HikariDataSource dataSource;

    //初始化连接池
    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER);
        hikariConfig.setJdbcUrl(URL);
        hikariConfig.setUsername(USER);
        hikariConfig.setPassword(PASSWORD);
        hikariConfig.setConnectionTimeout(CONN_TIMEOUT);
        hikariConfig.setIdleTimeout(IDLE_TIMEOUT);
        hikariConfig.setAutoCommit(false);
        hikariConfig.setMaximumPoolSize(MAXSUM_POOL_SIZE);
        dataSource = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
