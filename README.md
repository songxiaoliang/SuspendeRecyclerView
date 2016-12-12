# SuspendeRecyclerView
一个悬浮窗提示的RecyclerView动态效果显示的封装。
## Development Help
#### xml布局文件中引入：
    <com.example.song.rxjava2test.SuspendedRecyclerView
        android:id="@+id/ry"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />          
#### Activity或Fragment中初始化：
##### 注册悬浮窗视图：
recyclerView.setSuspendedView(tvShow);
##### 注册更新悬浮窗监听事件：
 recyclerView.setSuspendedListener(new SuspendedRecyclerView.SuspendedListener() {
            @Override
            public void changeSuspendedContent(int currentPosition) {
                tvShow.setText(beanList.get(currentPosition).text);
            }
        });
