package lib;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import net.sf.json.JSONObject;


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
        } catch (Exception
                e) {
            response = "";
        }
        return response;
    }
    public String sendPostData(String POST_URL, String content)
            throws Exception {
        HttpURLConnection connection=null;
        DataOutputStream out=null;
        BufferedReader reader=null;
        String line = "";
        String result="";
        try {
            URL postUrl = new URL(POST_URL);
            connection= (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.connect();
             
            out = new DataOutputStream(connection.getOutputStream());
            //content = URLEncoder.encode(content, "utf-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符�?8位的字符形式写道流里�?
            out.writeBytes(content);
            out.flush();
            out.close();
            //获取结果
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));// 设置编码
            while ((line = reader.readLine()) != null) {
                result=result+line;
            }        
            return result;
        } catch (Exception e) {
            throw e;
        }finally
        {
            if(out!=null)
            {
                out.close();
                out=null;                
            }
            if(reader!=null)
            {
                reader.close();
                reader=null;                
            }
            connection.disconnect();
        }
    }
}

