package com.example.song.rxjava2test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 悬浮窗显示效果 RecyclerView
 * Created by Song on 2016/12/12.
 */
public class SuspendedRecyclerView extends RecyclerView {

    private boolean isUpdate;
    private Context mContetx;
    private View mSuspended;
    private View mNextItemView;
    private int mSuspendedHeight;
    private int mCurrentPosition;
    private SuspendedListener mSuspendedListener;
    private LinearLayoutManager mLinearLayoutManager;

    public SuspendedRecyclerView(Context context) {
        this(context, null);
    }

    public SuspendedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuspendedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContetx = context;
        init();
    }

    private void init() {
        // 竖直线性布局排列
        mLinearLayoutManager = new LinearLayoutManager(mContetx);
        setLayoutManager(mLinearLayoutManager);
        this.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                mNextItemView = mLinearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if(mNextItemView == null) {
                    return;
                }
                /**
                 * 第二个Item距离顶部的高度小于悬浮窗的高度，此时让悬浮窗跟随列表上移而移动。
                 */
                if(mNextItemView.getTop() < mSuspendedHeight) {
                    mSuspended.setY(-(mSuspendedHeight - mNextItemView.getTop()));
                }

                /**
                 * 如果当前的Item位置不等于mCurrentPosition，也就是下一个Item又滑动上来。
                 * 此时我们更新悬浮窗，并固定显示在顶部
                 */
                if(mCurrentPosition != mLinearLayoutManager.findFirstVisibleItemPosition()) {
                    if(!isUpdate) {
                        // 更新当前位置
                        mCurrentPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                        // 将悬浮窗固定
                        mSuspended.setY(0);
                        // 更新悬浮条内容
                        if(mSuspendedListener != null) {
                            mSuspendedListener.changeSuspendedContent(mCurrentPosition);
                        }
                        isUpdate =  true;
                    } else {
                        isUpdate = false;
                    }
                }
            }
        });
    }

    /**
     * 设置悬浮窗,并初始化悬浮窗高度
     * @param suspended
     */
    public void setSuspendedView(View suspended) {
        this.mSuspended = suspended;
        suspended.post(new Runnable() {
            @Override
            public void run() {
                mSuspendedHeight = mSuspended.getHeight();
            }
        });
    }

    /**
     * 悬浮窗更新监听
     * currentPosition : 当前显示的第一个Item位置
     */
    interface SuspendedListener {
        void changeSuspendedContent(int currentPosition);
    }

    /**
     * 注册浮窗更新监听
     * @param suspendedListener
     */
    public void setSuspendedListener(SuspendedListener suspendedListener) {
        this.mSuspendedListener = suspendedListener;
    }

}
