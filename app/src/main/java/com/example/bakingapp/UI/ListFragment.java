package com.example.bakingapp.UI;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Adapters.IngredientAdapter;
import com.example.bakingapp.Adapters.StepsAdapter;
import com.example.bakingapp.Models.BakingModel;
import com.example.bakingapp.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {
    private BakingModel data;
    private StepsAdapter mStepsAdapter;
    private IngredientAdapter mIngredientAdapter;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;
    @BindView(R.id.ingredient_recycler_view)
    RecyclerView ingredientRecyclerView;


    private String allData;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allData = getArguments().getString(ListDetailActivity.DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        data = new Gson().fromJson(allData, BakingModel.class);
        name.setText(data.getName());

        mIngredientAdapter = new IngredientAdapter(data.getIngredients(), getContext());
        LinearLayoutManager ingredientLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ingredientRecyclerView.setLayoutManager(ingredientLayoutManager);
        ingredientRecyclerView.setAdapter(mIngredientAdapter);

        mStepsAdapter = new StepsAdapter(data.getSteps(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        stepsRecyclerView.setLayoutManager(layoutManager);
        stepsRecyclerView.setAdapter(mStepsAdapter);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
