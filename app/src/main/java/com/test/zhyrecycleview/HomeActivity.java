package com.test.zhyrecycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 *  主要是通过 onOptionsItemSelected 来设置效果的
 * */
public class HomeActivity extends ActionBarActivity{

	private RecyclerView mRecyclerView;
	private List<String> mDatas;
	private HomeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_recyclerview);

		initData(); // 模拟数据

		mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
		mAdapter = new HomeAdapter(this, mDatas);

		 // 瀑布流
		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
		mRecyclerView.setAdapter(mAdapter);

		 // 分割线
		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
		// 设置item动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		initEvent(); // 点击事件

	}

	private void initEvent(){
		mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener(){
			@Override
			public void onItemClick(View view, int position){
				Toast.makeText(HomeActivity.this, position + " click",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position){
				Toast.makeText(HomeActivity.this, position + " long click",Toast.LENGTH_SHORT).show();
			}
		});
	}

	protected void initData(){
		mDatas = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++){
			mDatas.add("" + (char) i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.id_action_add:
			mAdapter.addData(1); // 添加一个 Insert One
			break;
		case R.id.id_action_delete:
			mAdapter.removeData(1); // remove(position)
			break;
		case R.id.id_action_gridview: // gridView
			mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
			break;
		case R.id.id_action_listview: // listView
			mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
			break;
		case R.id.id_action_horizontalGridView: // 瀑布流
			mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
					StaggeredGridLayoutManager.HORIZONTAL));
			break;

		case R.id.id_action_staggeredgridview: // 开启新的 Activity
			Intent intent = new Intent(this , StaggeredGridLayoutActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}

}
