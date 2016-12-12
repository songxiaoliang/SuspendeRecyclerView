package com.example.song.rxjava2test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvShow;
    private List<Bean> beanList;
    private SuspendedRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initSuspendAndAdapter();
        setListener();
    }

    private void initView() {
        recyclerView = (SuspendedRecyclerView) findViewById(R.id.ry);
        tvShow = (TextView) findViewById(R.id.tv_show);
    }

    private void initData() {
        beanList = new ArrayList<>(20);
        Bean bean;
        for (int i = 0; i < 20; i++) {
            bean = new Bean();
            bean.text = "第"+i+"个Item";
            beanList.add(bean);
        }
        tvShow.setText(beanList.get(0).text);
    }

    private void initSuspendAndAdapter() {
        recyclerView.setAdapter(new MyAdapter(this,beanList));
        recyclerView.setSuspendedView(tvShow);
    }

    private void setListener() {
        recyclerView.setSuspendedListener(new SuspendedRecyclerView.SuspendedListener() {
            @Override
            public void changeSuspendedContent(int currentPosition) {
                tvShow.setText(beanList.get(currentPosition).text);
            }
        });
    }

}
