package com.example.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;
import com.example.bakingapp.UI.ListDetailActivity;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    private List<BakingModel> mainData;
    private Context context;

    public MainListAdapter(List<BakingModel> mainData, Context context) {
        this.mainData = mainData;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_text_view)
        TextView mTitle;
        @BindView(R.id.servings_text_view)
        TextView mServings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListDetailActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, new GsonBuilder().create().toJson(mainData.get(getAdapterPosition()), BakingModel.class));
                    context.startActivity(intent);
                }
            });
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
        viewHolder.mTitle.setText(mainData.get(i).getName());
        viewHolder.mServings.setText(mainData.get(i).getServings().toString());
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }
}
