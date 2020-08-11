package com.example.recipe.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.models.Recipe
import com.example.recipe.models.RecipeData
import com.example.recipe.repositories.RecipeRepository


//
// Created by Admin on 8/10/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

class RecipeListViewModel : ViewModel() {
    private val mRecipeRepository: RecipeRepository = RecipeRepository

     fun getRecipe(): LiveData<MutableList<RecipeData>> {
        return mRecipeRepository.getRecipes()
    }

    fun searchRecipesApi(query:String,pageNumber:Int){
        mRecipeRepository.searchRecipesApi(query, pageNumber)
    }
}