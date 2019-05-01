package com.example.bakingapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bakingapp.Adapters.IngredientAdapter;
import com.example.bakingapp.Adapters.StepsAdapter;
import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDetailActivity extends AppCompatActivity {
    private BakingModel data;
    private StepsAdapter mStepsAdapter;
    private IngredientAdapter mIngredientAdapter;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;
    @BindView(R.id.ingredient_recycler_view)
    RecyclerView ingredientRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        ButterKnife.bind(this);

        String allData = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        data = new Gson().fromJson(allData, BakingModel.class);

        name.setText(data.getName());

        mIngredientAdapter = new IngredientAdapter(data.getIngredients(), this);
        LinearLayoutManager ingredientLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        ingredientRecyclerView.setLayoutManager(ingredientLayoutManager);
        ingredientRecyclerView.setAdapter(mIngredientAdapter);

        mStepsAdapter = new StepsAdapter(data.getSteps(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        stepsRecyclerView.setLayoutManager(layoutManager);
        stepsRecyclerView.setAdapter(mStepsAdapter);

    }
}
