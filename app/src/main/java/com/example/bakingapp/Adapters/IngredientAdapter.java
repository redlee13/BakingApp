package com.example.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Models.Ingredient;
import com.example.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private List<Ingredient> mIngredientList;
    private Context mContext;

    public IngredientAdapter(List<Ingredient> ingredientList, Context context) {
        mIngredientList = ingredientList;
        mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ingredient_tv)
        TextView mIngredient;
        @BindView(R.id.quantity_tv)
        TextView mQuantity;
        @BindView(R.id.measure_tv)
        TextView mMeasure;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ingredient_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mIngredient.setText(mIngredientList.get(i).getIngredient());
        viewHolder.mQuantity.setText(String.valueOf(mIngredientList.get(i).getQuantity()));
        viewHolder.mMeasure.setText(mIngredientList.get(i).getMeasure());

    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }
}


