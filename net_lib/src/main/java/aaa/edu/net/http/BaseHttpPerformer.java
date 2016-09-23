package aaa.edu.net.http;

import aaa.edu.net.request.BaseHttpRequest;
import aaa.edu.net.response.Response;

/**
 * Created by aaa on 2016/9/22.
 */
public interface BaseHttpPerformer {
     Response performRequest(BaseHttpRequest bhr);
}
