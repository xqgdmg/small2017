package com.example.text.listviewpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mLv = (ListView) findViewById(R.id.lv);

//        List<String> list = new ArrayList<String>();

        LvAdapter adapter = new LvAdapter(Contants.imgList,MainActivity.this);

        mLv.setAdapter(adapter);
     }
}
