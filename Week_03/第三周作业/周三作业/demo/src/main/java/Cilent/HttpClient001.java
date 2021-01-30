package Cilent;

import okhttp3.*;


import java.io.IOException;

public class HttpClient001 {
    public static void main(String[] args)  {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("http://localhost:8802").build();
        try (Response response = okHttpClient.newCall(request).execute()){
            ResponseBody body = response.body();
            if (response.isSuccessful()) {
                System.out.println(body.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
