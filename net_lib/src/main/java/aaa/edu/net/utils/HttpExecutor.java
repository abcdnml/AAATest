package aaa.edu.net.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import aaa.edu.net.http.BaseHttpPerformer;
import aaa.edu.net.request.BaseHttpRequest;

/**
 * Created by aaa on 2016/9/25.
 */

public class HttpExecutor {
    private static Executor serialExecutor=Executors.newSingleThreadExecutor();
    private static Executor parallelExecutor=Executors.newSingleThreadExecutor();

    private BlockingQueue<BaseHttpRequest<?>> requestQueue;
    private BaseHttpPerformer httpPerformer;

    public HttpExecutor(BlockingQueue<BaseHttpRequest<?>> queue, BaseHttpPerformer performer)
    {
        requestQueue=queue;
        httpPerformer=performer;
    }

}
