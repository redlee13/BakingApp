package com.example.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    private List<BakingModel> data;
    private Context context;

    public MainListAdapter(List<BakingModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_view_item)
        TextView mTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTitle.setText(data.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
