package com.example.recipe.requets

import androidx.lifecycle.LiveData
import com.example.recipe.models.Recipe
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query


//
// Created by Admin on 8/8/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

interface RecipeApi {

    // SEARCH
    @GET("api/search")
    fun searchRecipe(
        @Query("key") key: String?,
        @Query("q") query: String?,
        @Query("page") page: String?
    ): Call<Recipe?>?

    // GET RECIPE REQUEST
    @GET("api/get")
    fun getRecipe(
        @Query("rId") recipe_id: String?
    ): Call<Recipe?>?
}