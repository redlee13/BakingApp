package com.example.bakingapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDetailActivity extends AppCompatActivity {
    private BakingModel data;
    @BindView(R.id.test)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle!= null){
//            data = bundle.getParcelable(Intent.EXTRA_TEXT);
//            test.setText(data.getName());
        }


    }
}
