package com.example.recipe.requets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipe.AppExecutors
import com.example.recipe.models.Recipe
import com.example.recipe.models.RecipeData
import com.example.recipe.util.KEY
import com.example.recipe.util.NETWORK_TIMEOUT
import retrofit2.Call
import java.util.concurrent.TimeUnit


//
// Created by Admin on 8/10/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

object RecipeApiClient {
    private const val TAG = "RecipeApiClient"
    private val mRecipes: MutableLiveData<MutableList<RecipeData>> = MutableLiveData()
    private var mRetrieveRecipesRunnable: RetrieveRecipesRunnable? = null

    fun getRecipes(): LiveData<MutableList<RecipeData>> {
        return mRecipes
    }

    fun searchRecipesApi(query: String, pageNumber: Int) {
        if (mRetrieveRecipesRunnable != null)
            mRetrieveRecipesRunnable = null
        mRetrieveRecipesRunnable = RetrieveRecipesRunnable(query, pageNumber)
        val handler = AppExecutors.networkIO().submit(mRetrieveRecipesRunnable)

        AppExecutors.networkIO().schedule({ //let the user know its timed out
            handler.cancel(true)
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    private class RetrieveRecipesRunnable(var query: String, var pageNumber: Int) : Runnable {
        var cancelRequest: Boolean = false
        override fun run() {
            var response = getRecipes(query, pageNumber)?.execute()
            if (cancelRequest)
                return
            if (response?.code() == 200) {
                Log.e(TAG, "run: success data is ${response.body()}" )
                var list: MutableList<RecipeData>? =
                    response.body()?.recipes as MutableList<RecipeData>?
                if (pageNumber == 1) {
                    mRecipes.postValue(list)
                } else {
                    var currentRecipes: MutableList<RecipeData>? = mRecipes.value
                    currentRecipes?.addAll(list!!)
                }
            } else {
                var error = response?.errorBody()?.string()
                Log.e(TAG, "run: $error")
                mRecipes.postValue(null)
            }
        }

        private fun getRecipes(query: String, pageNumber: Int): Call<Recipe?>? {
            return ServiceGenerator.getRecipeApi().searchRecipe(KEY, query, pageNumber.toString())
        }

        private fun cancelRequest() {
            Log.e(TAG, "cancelRequest: canceling the search request.")
            cancelRequest = true
        }
    }
}