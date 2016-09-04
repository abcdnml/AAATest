package com.aaa.test.materialdesign;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aaa.test.materialdesign.tab.FirstFragment;
import com.aaa.test.materialdesign.tab.SecondFragment;
import com.aaa.test.materialdesign.tab.ThirdFragment;

public class Main3Activity extends AppCompatActivity {

    Fragment[] fragments;
    private ViewPager vp_pager;
    TabLayout mTabLayout;

    private FragmentManager fm = getSupportFragmentManager();


    FragmentPagerAdapter pa = new FragmentPagerAdapter(fm) {
        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main3);
        setSupportActionBar(toolbar);
        initView();
        initPage();
    }

    private void initView() {
        vp_pager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void initPage() {
        fragments = new Fragment[3];
        fragments[0] = new FirstFragment();
        fragments[1] = new SecondFragment();
        fragments[2] = new ThirdFragment();

        vp_pager.setOffscreenPageLimit(2);

        vp_pager.setAdapter(pa);
        vp_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_pager.setCurrentItem(tab.getPosition());//点击哪个就跳转哪个界面
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        TabLayout.Tab tab1 = mTabLayout.newTab();
        tab1.setIcon(getResources().getDrawable(R.drawable.selector_tab_icon));
        tab1.setText("tab1");
        mTabLayout.addTab(tab1);
        TabLayout.Tab tab2 = mTabLayout.newTab();
        tab2.setIcon(getResources().getDrawable(R.drawable.selector_tab_icon));
        tab2.setText("tab2");
        mTabLayout.addTab(tab2);
        TabLayout.Tab tab3 = mTabLayout.newTab();
        tab3.setIcon(getResources().getDrawable(R.drawable.selector_tab_icon));
        tab3.setText("tab3");
        mTabLayout.addTab(tab3);

        //mTabLayout.setTabTextColors(Color.WHITE,Color.RED);
    }
}
