package com.aaa.test.materialdesign;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    List<String> lst=new ArrayList<String>();
    FloatingActionButton fab_plus,fab_minus;
    int i=0;
    TabPagerAdapter adapter;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        fab_plus=(FloatingActionButton)findViewById(R.id.fab_add_tab) ;
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                lst.add("tab"+i);
                adapter.notifyDataSetChanged();
            }
        });
        fab_minus=(FloatingActionButton)findViewById(R.id.fab_del_tab);
        fab_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lst.size()>0)
                {
                    lst.remove(lst.size() - 1);
                    i--;
                    adapter.notifyDataSetChanged();
                    Log.i("aaaaa", "current position" + tabLayout.getSelectedTabPosition());
                    if (tabLayout.getSelectedTabPosition() == -1) {
                        if (tabLayout.getTabCount() > 0) {
                            tabLayout.getTabAt(tabLayout.getTabCount() - 1).select();
                        }
                    }
                }
            }
        });
        // 获取ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_view);

        // 构造一个TabPagerAdapter对象
        adapter = new TabPagerAdapter();

        // 获取ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        // 设置TabLayout模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // 设置ViewPager的适配器
        viewPager.setAdapter(adapter);

        // 设置ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
    private class TabPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return lst.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tvContent = new TextView(Main4Activity.this);
            tvContent.setText(lst.get(position));
            tvContent.setGravity(Gravity.CENTER);
            container.addView(tvContent,
                    ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
            return tvContent;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return lst.get(position);
        }
    }
}
