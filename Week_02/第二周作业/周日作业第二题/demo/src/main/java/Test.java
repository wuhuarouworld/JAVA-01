import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.time.Duration;


import java.net.URI;

public class Test {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("http://localhost:8801")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (response.isSuccessful()) {
                System.out.println(body.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
