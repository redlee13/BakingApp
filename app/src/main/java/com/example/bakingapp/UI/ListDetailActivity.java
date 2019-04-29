package com.example.bakingapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bakingapp.Adapters.StepsAdapter;
import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDetailActivity extends AppCompatActivity {
    private BakingModel data;
    private StepsAdapter mStepsAdapter;

    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        ButterKnife.bind(this);

        String allData = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        data = new Gson().fromJson(allData, BakingModel.class);
        test.setText(data.getName());

        mStepsAdapter = new StepsAdapter(data.getSteps(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        stepsRecyclerView.setLayoutManager(layoutManager);
        stepsRecyclerView.setAdapter(mStepsAdapter);

    }
}
