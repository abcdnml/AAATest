package com.aaa.test.materialdesign.tab;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aaa.test.materialdesign.R;
import com.aaa.test.materialdesign.behavior.CustomTextView;

public class BehaviorTestActivity extends AppCompatActivity {

    TextView tv_test1;
    CustomTextView ctv_text;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_test);

        tv_test1=(TextView)findViewById(R.id.tv_test1);
        ctv_text=(CustomTextView)findViewById(R.id.ctv_text);
        handler.sendEmptyMessage(1);
    }
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("aaa","i="+i);
            switch (msg.what){
                case 1:
                    if(i<=100){
                        i+=4;
                        ViewGroup.LayoutParams params=tv_test1.getLayoutParams();
                        params.height=params.height+10;
                        tv_test1.setLayoutParams(params);
                        //tv_test1.setY(i);

                        if(i==100){
                            sendEmptyMessage(2);
                        }else{
                            sendEmptyMessageDelayed(1,40);
                        }
                    }
                    break;
                case 2:
                    if(i>=0){
                        i-=4;
                        ViewGroup.LayoutParams params=tv_test1.getLayoutParams();
                        params.height=params.height-10;
                        tv_test1.setLayoutParams(params);
                        //tv_test1.setY(i);
                        if(i==0){
                            sendEmptyMessage(1);
                        }else{
                            sendEmptyMessageDelayed(2,40);
                        }

                    }

                    break;
            }


        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
        handler.removeMessages(2);
    }
}
