package com.test.zhyrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 *  RecyclerView.Adapter<HomeAdapter.MyViewHolder> 其实是强制使用 ViewHolder
 * */
class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

	private List<String> mDatas;
	private LayoutInflater mInflater;

	/**
	 *  item 点击，自定义接口
	 * */
	public interface OnItemClickLitener{
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}

	 // 接口
	private OnItemClickLitener mOnItemClickLitener;

	/**
	 *  点击事件
	 * */
	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
		this.mOnItemClickLitener = mOnItemClickLitener;
	}
	
	/**
	 *  构造方法
	 * */
	public HomeAdapter(Context context, List<String> datas){
		mInflater = LayoutInflater.from(context);
		mDatas = datas;
	}

	/**
	 *  onCreateViewHolder 布局
	 * */
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_home, parent, false));
		return holder;
	}

	/**
	 *  onBindViewHolder 设置数据 ， 设置  View.OnClickListenre 点击事件
	 * */
	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position){
		holder.tv.setText(mDatas.get(position));

		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null){
			holder.itemView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					int pos = holder.getLayoutPosition(); // RecyclerView 的 ViewHolder 可以获得条目点击的 position
					mOnItemClickLitener.onItemClick(holder.itemView, pos); // 传出点击事件
				}
			});
			
			holder.itemView.setOnLongClickListener(new OnLongClickListener(){
				@Override
				public boolean onLongClick(View v){
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					removeData(pos); // 接下来会调用 RecyclerView 的 notifyItemRemoved 方法
					return false;
				}
			});
		}
	}

	@Override
	public int getItemCount()
	{
		return mDatas.size();
	}

	public void addData(int position){
		mDatas.add(position, "Insert One");
		notifyItemInserted(position);
	}


	public void removeData(int position){
		mDatas.remove(position);
		notifyItemRemoved(position); // 这个玩意，mObservable.notifyItemRangeRemoved(position, 1);用观察者发出了信号
	}

	class MyViewHolder extends RecyclerView.ViewHolder{
		TextView tv;

		public MyViewHolder(View view){
			super(view);
			tv = (TextView) view.findViewById(R.id.id_num);
		}
	}
}