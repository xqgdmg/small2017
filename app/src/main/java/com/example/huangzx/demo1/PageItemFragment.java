package com.example.huangzx.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
* 单个fragment 的布局
*/
public class PageItemFragment extends Fragment {

    public static final String PAGER = "PAGER";
    private int mPage;

    public static PageItemFragment newInstance(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGER, page);
        PageItemFragment pageFragment = new PageItemFragment();

         // 为什么官方推荐Fragment.setArguments(Bundle bundle)这种方式来传递参数，而不推荐通过构造方法直接来传递参数呢？
         // 我们可以知道Activity重新创建时，会重新构建它所管理的Fragment，原先的Fragment的字段值将会全部丢失，但是通过Fragment.setArguments(Bundle bundle)方法设置的bundle会保留下来。
         // attache activity 之前调用
        // 横竖屏切换的时候不会因为 activity 重新绘二丢失数据
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(PAGER);
    }

    /*
    *  onCreateView 布局
    */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return view;
    }
}
