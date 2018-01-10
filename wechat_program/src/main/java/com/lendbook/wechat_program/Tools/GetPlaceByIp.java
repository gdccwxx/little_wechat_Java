package com.lendbook.wechat_program.Tools;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetPlaceByIp {
    private String response;
    public String getResponse(String Url){
        try {
            URL url = new URL(Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(1000);
            byte [] rp = new byte[1024];
            int n;
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            InputStream inputStream = httpURLConnection.getInputStream();
            while ((n = inputStream.read(rp)) != -1){
                outStream.write(rp,0,n);
            }
            inputStream.close();
            response = new String(outStream.toByteArray());
        } catch (Exception e) {
            response = "";
        }
        return response;
    }

}
