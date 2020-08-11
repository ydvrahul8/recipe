package com.example.recipe.repositories

import androidx.lifecycle.LiveData
import com.example.recipe.models.RecipeData
import com.example.recipe.requets.RecipeApiClient


//
// Created by Admin on 8/10/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//
object RecipeRepository {
    private val mRecipeApiClient = RecipeApiClient
    fun getRecipes(): LiveData<MutableList<RecipeData>> {
        return mRecipeApiClient.getRecipes()
    }

    fun searchRecipesApi(query: String, number: Int) {
        var pageNumber = number
        if (pageNumber == 0)
            pageNumber = 1
        mRecipeApiClient.searchRecipesApi(query, pageNumber)
    }
}
