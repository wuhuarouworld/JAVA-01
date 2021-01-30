import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer003 {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors() + 2);
            final ServerSocket serverSocket = new ServerSocket(8803);
            while (true) {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(5);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset:utf-8");
            String body = "hello,nio";
            printWriter.println("Content-length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
