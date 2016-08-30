package aaa.edu.net.utils;

/**
 * Created by aaa on 2016/6/23.
 */
public interface RequestListener<T> {
    void onComplete(int returnCode,T result,String msg);
}
