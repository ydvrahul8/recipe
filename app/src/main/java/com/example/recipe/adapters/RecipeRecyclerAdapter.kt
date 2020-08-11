package com.example.recipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe.R
import com.example.recipe.models.RecipeData
import kotlin.math.roundToInt


//
// Created by Admin on 8/11/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

class RecipeRecyclerAdapter(
    private val onRecipeListener: OnRecipeListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<RecipeData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            RECIPE_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recipe_list_item, parent, false)
                return RecipeViewHolder(view, onRecipeListener)
            }
            LOADING_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_loading, parent, false)
                return LoadingViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recipe_list_item, parent, false)
                return RecipeViewHolder(view, onRecipeListener)
            }
        }

    }

    override fun getItemCount(): Int {
        if (list != null)
            return list!!.size
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType == RECIPE_VIEW) {
            val data = holder as RecipeViewHolder
            data.title.text = list!![position].title
            data.publisher.text = list!![position].publisher
            data.socialScore.text = list!![position].social_rank!!.roundToInt().toString()
            Glide.with(holder.itemView.context).load(list!![position].image_url)
                .placeholder(R.drawable.ic_launcher_background).into(data.image)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list!![position].title.equals("loading...", true))
            LOADING_VIEW
        else
            RECIPE_VIEW
    }

    private fun isLoading(): Boolean {
        if (list != null)
            if (list!!.size > 0) {
                if (list!![list!!.size - 1].title.equals("loading...", true))
                    return true
            }
        return false
    }

    fun displayLoading() {
        if (!isLoading()) {
            val recipe = RecipeData()
            recipe.title = "Loading..."
            val recipeList = ArrayList<RecipeData>()
            recipeList.add(recipe)
            list = recipeList
            notifyDataSetChanged()
        }
    }

    fun setRecipe(recipes: MutableList<RecipeData>) {
        list = recipes
        notifyDataSetChanged()
    }

    companion object {
        private const val RECIPE_VIEW = 1
        private const val LOADING_VIEW = 0
    }
}