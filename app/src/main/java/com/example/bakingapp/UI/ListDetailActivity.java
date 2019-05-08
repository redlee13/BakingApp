package com.example.bakingapp.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.bakingapp.Adapters.MainListAdapter;
import com.example.bakingapp.R;

import butterknife.ButterKnife;

public class ListDetailActivity extends AppCompatActivity {
    public static final String DATA = "json_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        ButterKnife.bind(this);

        String allData = getIntent().getStringExtra(MainListAdapter.MAIN_LIST);

        Bundle bundle = new Bundle();
        bundle.putString(DATA, allData);

        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.list_container, listFragment)
                .commit();

    }
}
