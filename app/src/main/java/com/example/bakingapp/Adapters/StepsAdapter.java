package com.example.bakingapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Models.Step;
import com.example.bakingapp.R;
import com.example.bakingapp.UI.ListDetailActivity;
import com.example.bakingapp.UI.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private List<Step> mStepList;
    private Context mContext;

    public StepsAdapter(List<Step> stepList, Context context) {
        mStepList = stepList;
        mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.step_item)
        TextView mStep;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createFragment(getAdapterPosition());
                }
            });
        }

    }

    private void createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putParcelableArrayList("all", new ArrayList<>(mStepList));

        VideoFragment videoFragment = new VideoFragment();
        videoFragment.setArguments(bundle);

        if (mContext.getResources().getBoolean(R.bool.isTablet)) {
            ((ListDetailActivity) mContext).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.video_container, videoFragment)
                    .commit();
        } else {
            ((ListDetailActivity) mContext).getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.list_container, videoFragment)
                    .commit();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.step_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mStep.setText(mStepList.get(i).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }
}
