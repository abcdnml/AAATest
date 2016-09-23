package aaa.edu.net.http;

import org.apache.http.StatusLine;
import org.apache.http.message.BasicStatusLine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import aaa.edu.net.request.BaseHttpRequest;
import aaa.edu.net.response.Response;

/**
 * Created by aaa on 2016/9/22.
 */
public  class HttpUrlConnPerformer implements BaseHttpPerformer {

    HttpUrlConnConfig config=new HttpUrlConnConfig();
    @Override
    public Response performRequest(BaseHttpRequest request) {


        //获取网站返回的输入流
        InputStream in = null;
        //每次读的字节数
        byte[] data = new byte[1024];
        //每次读到的字节数，一般是1024，如果到了最后一行就会少于1024，到了末尾就是 -1
        int len = 0;
        //本地的输出流
        try
        {
            HttpURLConnection conn=getConnection(request.getUrl(),request.getRequestType().get());

            DataOutputStream os=new DataOutputStream(conn.getOutputStream());
            os.write(request.getByteParams());
            os.close();


            int responseCode = conn.getResponseCode();
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            }
            // 状态行数据
            StatusLine responseStatus = new BasicStatusLine(protocolVersion,
                    connection.getResponseCode(), connection.getResponseMessage());
            // 构建response
            Response response = new Response(responseStatus);
            // 设置response数据
            response.setEntity(entityFromURLConnection(connection));
            addHeadersToResponse(response, connection);
            if(conn.getResponseCode() == 200)
            {

                InputStreamReader is = new InputStreamReader(conn.getInputStream());

                BufferedReader br = new BufferedReader(is);
                String line = "";
                StringBuffer sb = new StringBuffer();
                while((line = br.readLine()) != null)
                {

                    sb.append(line);
                }

                br.close();
                is.close();
                result = sb.toString();
            }
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    private HttpURLConnection getConnection(String urlString,String requestMethod) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //默认是get 方式
        conn.setRequestMethod(requestMethod);
        // 设置是否向connection输出，如果是post请求，参数要放在http正文内，因此需要设为true
        conn.setDoOutput(true);
        // Post 请求不能使用缓存
        conn.setUseCaches(false);
        //设置请求头 一般没特殊要求， 不需要
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        return conn;
    }
    private void writeParams(){
    }


}
