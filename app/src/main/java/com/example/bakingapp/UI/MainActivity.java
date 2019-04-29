package com.example.bakingapp.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.bakingapp.Adapters.MainListAdapter;
import com.example.bakingapp.Data.GetData;
import com.example.bakingapp.Data.RetrofitInstance;
import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;
import com.google.gson.JsonArray;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.main_recycler_view)
    RecyclerView mRecyclerView;
    private MainListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GetData data = RetrofitInstance.getRetrofit().create(GetData.class);
        Call<List<BakingModel>> call = data.getAllData();
        call.enqueue(new Callback<List<BakingModel>>() {
            @Override
            public void onResponse(Call<List<BakingModel>> call, Response<List<BakingModel>> response) {
                mAdapter = new MainListAdapter(response.body(), MainActivity.this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<BakingModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }
}
