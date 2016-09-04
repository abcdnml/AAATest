package com.aaa.test.materialdesign.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.aaa.test.materialdesign.R;

/**
 * Created by aaa on 2016/9/4.
 */
public class Test3Behavior extends CoordinatorLayout.Behavior{
    public Test3Behavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency){
        return dependency.getId()== R.id.nsv_scoll;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        Log.i("aaa", "dyConsumed"+ dyConsumed);
        int old = offsetTotal;
        int top = offsetTotal - dyConsumed;
        top = Math.max(top, -child.getHeight());
        top = Math.min(top, 0);
        offsetTotal = top;
        int delta = offsetTotal-old;
        //Log.i("aaa", "delta"+ delta);
        child.offsetTopAndBottom(-delta*2);
//        if(old==offsetTotal)
//        {
//            return ;
//        }else{
////            if(delta>600){
////                delta=600;
////            }
////            if(delta<-600){
////                delta=-600;
////            }
//            child.offsetTopAndBottom(-delta*10);
//        }
    }
    int offsetTotal=0;


    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        //这里提示 要检查是否为Coordnatorlayout的子View
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return super.onTouchEvent(parent, child, ev);
    }
}
