package com.example.huangzx.demo2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huangzx.demo1.R;

/**
 * Created by Chris on 2017/3/17.
 */
@SuppressLint("ValidFragment")
public class Fragment3 extends Fragment{


    private final String mText;

    public Fragment3(String text) {
        this.mText = text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText(mText);
        return view;
    }
}
