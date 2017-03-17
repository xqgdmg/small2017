package com.example.huangzx.demo1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class TabLayoutActivity1 extends AppCompatActivity {

    private static final String POSITION = "position";
    private MyFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    TabItem[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_1);

         // 设置 ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);

         // tabLayout 关联 ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

         // TabItem 数组
        tabs = new TabItem[tabLayout.getTabCount()];

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

             // 新建 Tab 设置 Tab 的布局
            TabLayout.Tab tab = tabLayout.getTabAt(i);  // tabLayout.getTabAt(i); 获取 tab
            tabs[i] = new TabItem(this);  // 手动给 tab 赋值，这个有点 low逼
            tab.setCustomView(tabs[i].getTabView()); // tab.setCustomView 给 tab 设置自定义的布局
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabs[position].setIndicVisibility(3); // 选中更改 tab 的位置，还是自己写的，感觉好low逼
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
     public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION,viewPager.getCurrentItem()); // 保存 ViewPager 的位置
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }
}
