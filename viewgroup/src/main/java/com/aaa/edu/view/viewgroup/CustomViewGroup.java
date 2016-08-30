package com.aaa.edu.view.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by aaa on 2016/8/16.
 */
public class CustomViewGroup extends ViewGroup
{
    int col;
    int row;
    int pressX;
    int pressY;
    int mWidth;
    int mHeight;
    int l=0,t=0,r=0,b=0;
    Scroller mScroller;
    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }
    private void init(Context context, AttributeSet attrs){
        mScroller=new Scroller(context);
        setTableRowAndColumn(context,attrs);
    }

    public void setTableRowAndColumn(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.table);
        col=ta.getInteger(R.styleable.table_col,0);
        row=ta.getInt(R.styleable.table_row,0);

        Log.i("aaaaaaaaaaa ","aaaaaaaa  col : "+ col +"      row "+row);
        Log.i("aaaaaaaaaaa ","aaaaaaaa  scroll x  : "+ mScroller.getCurrX() +"      y "+mScroller.getCurrY());
        ta.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount(); //子元素的个数
        int left = 0; //左边的距离
        int top=0;
        View child;
        //遍历布局子元素
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height= child.getMeasuredHeight();
            //调用每个子元素的layout方法去布局这个子元素，这里相当于默认第一个子元素占满了屏幕，后面的子元素就是在第一个屏幕后面紧挨着和屏幕一样大小的后续元素，所以left是一直累加的，top保持0，bottom保持第一个元素的高度，right就是left+元素的宽度

            if((i%col)==0&&i!=0)
            {
                left=0;
                top+=height;
            }
            child.layout(left, top, left + width,top+height);
            left += width;

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到宽和高的MODE和SIZE
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //测量所有子元素，先执行，不然后面拿不到第一个子元素的测量宽/高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //处理wrap_content的情况
        if (getChildCount() == 0)
        {
            //如果没有子元素，就设置宽高都为0（简化处理）
            setMeasuredDimension(0, 0);
        }else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            Log.i("aaaaaaaaaaa ","aaaaaaaa measure 1");
            //宽和高都是AT_MOST，则设置宽为第一个子元素的宽度乘以子元素的个数（这里默认每个子元素都和第一个元素一样的宽度）；高度设置为第一个元素的高度；
            //当然，我们最后用的时候子元素的宽度和高度就是屏幕的宽度和高度
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            int line=getChildCount()/(col+1)+1;
            if(getChildCount()<col){
                setMeasuredDimension(childWidth * getChildCount(), line*childHeight);
                mWidth=childWidth * getChildCount();
                mHeight=line*childHeight;
            }else{
                mWidth=col * getChildCount();
                mHeight=line*childHeight;
                setMeasuredDimension(childWidth * col, line*childHeight);
            }
        } else if (widthMode == MeasureSpec.AT_MOST) {
            Log.i("aaaaaaaaaaa ","aaaaaaaa measure 2");
            //这里只有宽度是AT_MOST，那就设置高度为系统测量的高度，宽度和第一个if中的一样
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            int line=getChildCount()/(col+1)+1;
            if(getChildCount()<col){
                setMeasuredDimension(childWidth * getChildCount(), heightSize);

                mWidth=childWidth * getChildCount();
                mHeight=heightSize;

            }else{
                setMeasuredDimension(childWidth * col, heightSize);

                mWidth=childWidth * col;
                mHeight=heightSize;
            }

        }
        //这里只有高度是AT_MOST，那就设置宽度为系统测量的宽度，高度和第一个if中的一样
        else if (heightMode == MeasureSpec.AT_MOST) {
            Log.i("aaaaaaaaaaa ","aaaaaaaa measure 5");
            int childHeight = getChildAt(0).getMeasuredHeight();
            int line=getChildCount()/(col+1)+1;
            setMeasuredDimension(widthSize, line*childHeight);

            mWidth=widthSize;
            mHeight=line*childHeight;
        }
        l=0;
        t=0;
        r=getWidth();
        b=getHeight();
        Log.i("aaaaaaaaaaa ","aaaaaaaa measure mWidth x  : "+ mWidth +"     mHeight "+mHeight);

        //宽度和高度都不是AT_MOST的情况在super方法中已经设置了：宽高都是系统测量的结果；
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action=event.getAction();
        float x=event.getX();
        float y=event.getY();
       // Log.i("aaaaaaaaaaa ","aaaaaaaa  scroll x  : "+ mScroller.getCurrX() +"      y "+mScroller.getCurrY());
       // Log.i("aaaaaaaaaaa ","aaaaaaaa  x  : "+ x +"      y "+y);
        Log.i("aaaaaaaaaaa ","aaaaaaaa  mWidth x  : "+ mWidth +"     mHeight "+mHeight);
        if(MotionEvent.ACTION_DOWN==action)
        {
            pressX=(int)x;
            pressY=(int)y;
        }else if(MotionEvent.ACTION_MOVE==action)
        {
            int detaX = (int)(pressX -  x); //每次滑动屏幕，屏幕应该移动的距离
            int detaY = (int)(pressY -  y ); //每次滑动屏幕，屏幕应该移动的距离

            if(l+detaX>0&&t+detaY>0&&r+detaX<mWidth&&b+detaY<mHeight){
                scrollBy(detaX, detaY);//开始缓慢滑屏咯。 detaX > 0 向右滑动 ， detaX < 0 向左滑动 ，
                pressX = (int)x;
                pressY = (int)y;

                l=l+detaX;
                t=t+detaY;
                r=r+detaX;
                b=b+detaY;
                //scrollTo(pressX,pressY);
            }



        }else if(MotionEvent.ACTION_UP==action)
        {
            pressX=0;
            pressY=0;
        }
        return true;
    }
}
