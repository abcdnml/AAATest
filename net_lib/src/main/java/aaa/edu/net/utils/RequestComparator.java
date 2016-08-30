package aaa.edu.net.utils;

import java.util.Comparator;

import aaa.edu.net.request.BaseHttpRequest;

/**
 * Created by aaa on 2016/6/20.
 */
public class RequestComparator implements Comparator<BaseHttpRequest> {

    @Override
    public int compare(BaseHttpRequest lhs, BaseHttpRequest rhs) {
        if (lhs.getPriority().getLevel() > rhs.getPriority().getLevel()) {
            return 1;
        } else if (lhs.getPriority().getLevel() == rhs.getPriority().getLevel()) {
            return 0;
        } else {
            return -1;
        }
    }
}
