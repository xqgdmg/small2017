package com.example.text.listviewpic;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.text.listviewpic.view.RatioLayout;
import com.squareup.picasso.Picasso;

/**
 * Created by Chris on 2017/3/13.
 */
public class LvAdapter extends BaseAdapter{
    Context mContext;
    String[] mList;
    Bitmap bitmap;

    public LvAdapter(String[] mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public Object getItem(int position) {
        return mList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder holder;
        if(convertView == null){
            holder = new MyViewHolder();
            //最好把false加上
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent,false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.ratioLayout = (RatioLayout) convertView.findViewById(R.id.ratio);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }

         // getView 里面开线程的问题 ，return convertView 已经执行，而子线程还没有运行完毕，导致所有图片都是空的
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        MainActivity mainActivity = (MainActivity) LvAdapter.this.mContext;
//
//
//                        bitmap = Picasso.with(mContext).load(mList[position]).get();
//
//                        mainActivity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                float ratio = bitmap.getWidth()/bitmap.getHeight();
//                                holder.imageView.setImageBitmap(bitmap); // 设置图片到控件
//                                holder.ratioLayout.setPicRatio(ratio); //
////                                notifyDataSetChanged();
//                            }
//                        });
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//
////
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

         // 关闭网络也能加载图片，说明 picasso 有默认缓存
        Picasso.with(mContext).load(mList[position]).placeholder(R.mipmap.ic_launcher).into(holder.imageView);

        return convertView;
    }

    private class MyViewHolder{
        ImageView imageView;
        RatioLayout ratioLayout;
    }
}
