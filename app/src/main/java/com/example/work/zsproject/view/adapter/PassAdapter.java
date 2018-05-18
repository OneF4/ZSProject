package com.example.work.zsproject.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.work.zsproject.R;

import java.util.List;

/**
 * 通过安检适配器
 */

public class PassAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;

    public PassAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pass_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
