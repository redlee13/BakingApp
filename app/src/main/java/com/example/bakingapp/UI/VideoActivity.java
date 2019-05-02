package com.example.bakingapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;

public class VideoActivity extends AppCompatActivity {
    private BakingModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String allData = getIntent().getStringExtra(Intent.EXTRA_TEXT);
    }
}
