package com.example.bakingapp.Data;

import com.example.bakingapp.Models.BakingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

    @GET("baking.json")
    Call<List<BakingModel>> getAllData();
}
