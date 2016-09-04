package com.aaa.test.materialdesign.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.aaa.test.materialdesign.R;

/**
 * Created by aaa on 2016/9/4.
 */
public class MyFirstBehavior extends CoordinatorLayout.Behavior
{
    public MyFirstBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //系统反射此构造方法
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //设置依赖View
        return dependency.getId() == R.id.tv_test1;
    }
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //触发的事件  当依赖View有变化时触发

        child.setY(dependency.getY()+dependency.getHeight());
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;//这里返回true，才会接受到后续滑动事件。
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//进行滑动事件处理
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
//当进行快速滑动
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
}
