package com.example.recipe.requets

import com.example.recipe.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//
// Created by Admin on 8/8/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

object ServiceGenerator {
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())

    private val retrofit:Retrofit = retrofitBuilder.build()

    private val recipeApi: RecipeApi = retrofit.create(RecipeApi::class.java)

    fun getRecipeApi():RecipeApi{
        return recipeApi
    }
}