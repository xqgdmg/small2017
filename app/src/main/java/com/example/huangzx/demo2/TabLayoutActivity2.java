package com.example.huangzx.demo2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.example.huangzx.demo1.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class TabLayoutActivity2 extends AppCompatActivity {

    private TabLayout tabLayout;                            //定义TabLayout
    private ViewPager mViewPager;                             //定义viewPager
    private FragmentPagerAdapter fragmentPagerAdapter;                               //定义adapter

    private List<Fragment> fragments;                                //定义要装fragment的列表
    private List<String> titles;                                     //tab名称列表

    private Fragment1 fragment1;              //热门推荐fragment
    private Fragment2 fragment2;            //热门收藏fragment
    private Fragment3 fragment3;                      //本月热榜fragment
    private Fragment4 fragment4;                 //今日热榜fragment
    private Fragment5 fragment5;
    private Fragment6 fragment6;
    private Fragment7 fragment7;
    private Fragment8 fragment8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_2);

        init();
    }

    private void init() {

        tabLayout = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        mViewPager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);

        //初始化各fragment
        fragment1 = new Fragment1("我是第1个Fragment");
        fragment2 = new Fragment2("我是第2个Fragment");
        fragment3 = new Fragment3("我是第3个Fragment");
        fragment4 = new Fragment4("我是第4个Fragment");
        fragment5 = new Fragment5("我是第5个Fragment");
        fragment6 = new Fragment6("我是第6个Fragment");
        fragment7 = new Fragment7("我是第7个Fragment");
        fragment8 = new Fragment8("我是第8个Fragment");

        //将fragment装进列表中
        fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        fragments.add(fragment6);
        fragments.add(fragment7);
        fragments.add(fragment8);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        titles = new ArrayList<>();
        titles.add("热门推荐");
        titles.add("热门收藏");
        titles.add("本月热榜");
        titles.add("今日热榜");
        titles.add("今日热榜");
        titles.add("今日热榜");
        titles.add("今日热榜");
        titles.add("今日热榜");

        //设置TabLayout的模式,也可以在 xml 中设置
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // 为 TabLayout 添加 tab 并设置名称
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));

        //viewpager加载adapter
        fragmentPagerAdapter = new FragmentPagerAdapter2(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(fragmentPagerAdapter);

        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(mViewPager);

        // 最后一个,利用反射的方法，设置下划线的宽度，设置大了明显不好看
//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(tabLayout, 10, 10);
//            }
//        });
    }

    /*
    * 利用反射的方法，设置下划线的宽度
    */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
