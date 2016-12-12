package com.example.song.rxjava2test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Song on 2016/12/9.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    List<Bean> mBeanList;
    LayoutInflater inflater;

    public MyAdapter(Context context,List<Bean> beanList) {
        inflater = LayoutInflater.from(context);
        this.mBeanList = beanList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.ry_layout,parent,false);
        MyHolder myHolder = new MyHolder(rootView);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Bean bean = mBeanList.get(position);
        holder.tv.setText(bean.text);

    }


    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
