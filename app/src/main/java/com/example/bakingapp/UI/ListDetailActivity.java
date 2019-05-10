package com.example.bakingapp.UI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bakingapp.Adapters.MainListAdapter;
import com.example.bakingapp.AppWidget;
import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;
import com.google.gson.Gson;

import butterknife.ButterKnife;

public class ListDetailActivity extends AppCompatActivity {
    public static final String DATA = "json_data";
    private static BakingModel data;
    public static final String PREF_WIDGET = "pref_";
    public static final String PREF_WIDGET_NAME = "pref_name";
    public static final String PREF_WIDGET_BODY = "pref_body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        ButterKnife.bind(this);

        String allData = getIntent().getStringExtra(MainListAdapter.MAIN_LIST);
        data = new Gson().fromJson(allData, BakingModel.class);
        this.setTitle(data.getName());

        Bundle bundle = new Bundle();
        bundle.putString(DATA, allData);

        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.list_container, listFragment)
                .commit();


    }

    private void saveIngredient() {
        SharedPreferences.Editor editor = getSharedPreferences(PREF_WIDGET, 0).edit();
        editor.putString(PREF_WIDGET_NAME, data.getName());
        String ingredients = "";
        for (int i = 0; i < data.getIngredients().size(); i++){
            String ingredient = data.getIngredients().get(i).getIngredient();
            String measure = data.getIngredients().get(i).getMeasure();
            Double quantity = data.getIngredients().get(i).getQuantity();

            ingredients = ingredients + (ingredient + "\n" + quantity + " " + measure + "\n");

        }
        editor.putString(PREF_WIDGET_BODY, ingredients);

        editor.apply();
        AppWidget.update(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.widget, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_to_widget){
            saveIngredient();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
