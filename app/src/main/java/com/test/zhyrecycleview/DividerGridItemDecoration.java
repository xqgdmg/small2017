package com.test.zhyrecycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 自定义 ItemDecoration ，其实是系统的 Drawable
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration{

	private static final int[] ATTRS = new int[] { android.R.attr.listDivider }; // 用的是 android 的 attr
	private Drawable mDivider;

	 // 构造方法, 需要获得 Drawable
	public DividerGridItemDecoration(Context context){
		final TypedArray a = context.obtainStyledAttributes(ATTRS);
		mDivider = a.getDrawable(0); // 获取到 Drawable
		a.recycle();
	}

	 // onDraw ，画水平和竖直
	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state){
		drawHorizontal(c, parent);
		drawVertical(c, parent);
	}

	/**
	 *  getSpanCount 获得 列数
	 * */
	private int getSpanCount(RecyclerView parent){
		// 列数
		int spanCount = -1;
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager){

			spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
		} else if (layoutManager instanceof StaggeredGridLayoutManager){
			spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
		}
		return spanCount;
	}

	/**
	 *  画水平的分割线
	 * */
	public void drawHorizontal(Canvas canvas, RecyclerView parent){
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++){
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			final int left = child.getLeft() - params.leftMargin;
			final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth(); // getIntrinsicWidth 连填充颜色也算
			final int top = child.getBottom() + params.bottomMargin;
			final int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top, right, bottom); // 设置边框
			mDivider.draw(canvas); // Drawable..draw() 画 Drawable 的边框和透明度等
		}
	}

	/**
	 *  画竖直的分割线
	 * */
	public void drawVertical(Canvas c, RecyclerView parent){
		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++){
			final View child = parent.getChildAt(i);

			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			final int top = child.getTop() - params.topMargin;
			final int bottom = child.getBottom() + params.bottomMargin;
			final int left = child.getRight() + params.rightMargin;
			final int right = left + mDivider.getIntrinsicWidth();

			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	/**
	 *  是否是最后一列
	 * */
	private boolean isLastColum(RecyclerView parent, int pos, int spanCount,int childCount){
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager){
			if ((pos + 1) % spanCount == 0){ // 如果是最后一列，则不需要绘制右边
				return true;
			}
		} else if (layoutManager instanceof StaggeredGridLayoutManager){
			int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
			if (orientation == StaggeredGridLayoutManager.VERTICAL){
				if ((pos + 1) % spanCount == 0){ // 如果是最后一列，则不需要绘制右边
					return true;
				}
			} else{
				childCount = childCount - childCount % spanCount;
				if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
					return true;
			}
		}
		return false;
	}

	/**
	 *  是否是最后一行
	 * */
	private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,int childCount){
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager){
			childCount = childCount - childCount % spanCount;
			if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
				return true;
		} else if (layoutManager instanceof StaggeredGridLayoutManager){
			int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
			// StaggeredGridLayoutManager 且纵向滚动
			if (orientation == StaggeredGridLayoutManager.VERTICAL){
				childCount = childCount - childCount % spanCount;
				// 如果是最后一行，则不需要绘制底部
				if (pos >= childCount)
					return true;
			} else{// StaggeredGridLayoutManager 且横向滚动
				if ((pos + 1) % spanCount == 0){ // 如果是最后一行，则不需要绘制底部
					return true;
				}
			}
		}
		return false;
	}

	/**
	 *  偏移量
	 * */
	@Override
	public void getItemOffsets(Rect outRect, int itemPosition,RecyclerView parent){
		int spanCount = getSpanCount(parent);
		int childCount = parent.getAdapter().getItemCount();
		if (isLastRaw(parent, itemPosition, spanCount, childCount)){ // 如果是最后一行，则不需要绘制底部
			outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
		} else if (isLastColum(parent, itemPosition, spanCount, childCount)){ // 如果是最后一列，则不需要绘制右边
			outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
		} else{
			outRect.set(0, 0, mDivider.getIntrinsicWidth(),mDivider.getIntrinsicHeight());
		}
	}
}
