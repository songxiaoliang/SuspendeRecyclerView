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
（1）recyclerView.setSuspendedListener(new SuspendedRecyclerView.SuspendedListener());
（2）实现SuspendedListener的changeSuspendedContent（int currentPosition）回调方法。
