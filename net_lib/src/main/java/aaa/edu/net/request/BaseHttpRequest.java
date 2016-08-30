package aaa.edu.net.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import aaa.edu.net.entity.HttpRequestType;
import aaa.edu.net.entity.Priority;
import aaa.edu.net.response.Response;
import aaa.edu.net.utils.RequestListener;

/**
 * Created by aaa on 2016/6/20.
 */
public abstract class BaseHttpRequest<T> {

    private static final String ENCODING_TYPE = "UTF-8";

    protected int serialNum;
    protected boolean isCancel = false;

    protected String url;
    protected boolean keepCache = false;
    protected Priority priority = Priority.NORMAL;
    protected HttpRequestType requestType = HttpRequestType.GET;
    protected RequestListener<T> requestListener;
    private Map<String, String> params = new HashMap<String, String>();

    public BaseHttpRequest(HttpRequestType type, String url, RequestListener<T> requestListener) {
        this.requestType = type;
        this.url = url;
        this.requestListener = requestListener;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + ENCODING_TYPE;
    }

    public byte[] preparePostParams() {
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                sb.append(param.getKey());
                sb.append('=');
                sb.append(param.getValue());
                sb.append('&');
            }
            try {
                String content = URLEncoder.encode(sb.toString(), ENCODING_TYPE);
                return content.getBytes();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public String prepareGetParams() {
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                sb.append(param.getKey());
                sb.append('=');
                sb.append(param.getValue());
                sb.append('&');
            }
            try {
                String content = URLEncoder.encode(sb.toString(), ENCODING_TYPE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


    public abstract T parseResponse(Response response);

    public void onGetResponse(Response response) {
        if (response != null) {
            T result = parseResponse(response);
            if (requestListener != null) {
                requestListener.onComplete(response.getStatusCode(), result, response.getMessage());
            }
        }
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public boolean isKeepCache() {
        return keepCache;
    }

    public void setKeepCache(boolean keepCache) {
        this.keepCache = keepCache;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public HttpRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(HttpRequestType requestType) {
        this.requestType = requestType;
    }

}
