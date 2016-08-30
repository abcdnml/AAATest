package aaa.edu.net.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import aaa.edu.net.entity.HostInfo;

/**
 * Created by aaa on 2016/6/20.
 */
public class URLManager {
    private static URLManager urlManager;
    private static Context context;
    private static final String RULFilename ="url.json";
    private static HostInfo hi;
    private URLManager(){

    }

    public static URLManager getInstance() {
        if(urlManager==null)
        {
            urlManager=new URLManager();
            hi=readURL();
        }
        return urlManager;
    }

    /**
     * 获取URL信息
     * @return
     * @throws Exception
     */
    private static HostInfo readURL () {
        String json = readAssertJsonFile();
        HostInfo hi = null;
        try {
            Gson gson = new Gson();
            hi = gson.fromJson(json, HostInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (hi == null) {
                throw new Exception("can not read url!");
            }else{
                return hi;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取url 文件
     * @return string
     */
    private static String  readAssertJsonFile(){
        AssetManager assetManager = context.getAssets();
        StringBuffer stringBuffer = new StringBuffer("");
        InputStream is=null;
        BufferedReader br=null;
        try {
            is = assetManager.open(RULFilename);
            br = new BufferedReader(new InputStreamReader(is));
            String str = null;
            while((str = br.readLine())!=null){
                stringBuffer.append(str);
            }
            is.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

}
