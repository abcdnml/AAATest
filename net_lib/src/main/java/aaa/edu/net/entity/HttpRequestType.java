package aaa.edu.net.entity;

/**
 * Created by aaa on 2016/6/23.
 */
public enum HttpRequestType {
    GET("GET"), POST("POST");

    String requestType = "GET";

    HttpRequestType(String type) {
        requestType = type;
    }

    public String get() {
        return requestType;
    }

    @Override
    public String toString() {
        return requestType;
    }
}
