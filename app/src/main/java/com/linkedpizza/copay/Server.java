package com.linkedpizza.copay;


import android.os.AsyncTask;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by MLH-Admin on 2/17/2018.
 */

class Server{

    private static volatile Server instance = null;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();
    private String bodyRespose;

    // Singleton.
    private Server(){}

    protected void post(String url, String json) throws IOException {
        SendJson sendJson = new SendJson(url, json);
        sendJson.execute();
    }

    protected String requestJson(String name, String email, String amount, String type, String reason) {
        return "{'type':" + type + ","
                + "'amount':" + amount + ","
                + "'reason':" + reason + ","
                + "'user':["
                + "{'name':'" + name + "','email':" + email + ",]}}";
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getBodyRespose(){
        return bodyRespose;
    }

    public static Server getInstance(){
        if (instance == null){
            synchronized (Server.class){
                if (instance == null){
                    instance = new Server();
                }
            }
        }
        return instance;
    }

    private class SendJson extends AsyncTask<String, String, String> {

        private String url;
        private String json;

        public SendJson(String url, String json) {
            // Get json.
            this.url = url;
            this.json = json;
        }

        @Override
        protected String doInBackground(String... strings) {
            // Send json to server.
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url + json)
                    .post(body)
                    .build();
            try {
                System.out.println("post at " + request);
                Response response = client.newCall(request).execute();

                System.out.println("post: " + response.body().string());
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
    }
}
