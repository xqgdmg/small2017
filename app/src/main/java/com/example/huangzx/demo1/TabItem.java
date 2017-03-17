package com.example.huangzx.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  单个 tab 的布局
 */
public class TabItem {

    public View getTabView(){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        mTvTitle = (TextView) view.findViewById(R.id.textView);
        mIndic = (ImageView) view.findViewById(R.id.imageView);
        return view;
    }

    public TabItem(Context context){
        this.context = context;
    }

    public void setTabTitle(CharSequence title){
        mTvTitle.setText(title);
    }

    public void setTabImg(int resId){
        mIndic.setImageResource(resId);
    }

    public void setIndicVisibility(int visible){
        if (mIndic.getVisibility() == View.VISIBLE)
            mIndic.setVisibility(View.INVISIBLE);
        else
            mIndic.setVisibility(View.VISIBLE);
    }

    private Context context;

    TextView mTvTitle;
    ImageView mIndic;
}
