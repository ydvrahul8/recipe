package com.example.recipe.adapters

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R


//
// Created by Admin on 8/11/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

class RecipeViewHolder(private val view: View, private val onRecipeListener: OnRecipeListener) :
    RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.recipe_title)
    val publisher = view.findViewById<TextView>(R.id.recipe_publisher)
    val socialScore = view.findViewById<TextView>(R.id.recipe_social_score)
    val image = view.findViewById<AppCompatImageView>(R.id.recipe_image)

    init {
        image.setOnClickListener { onRecipeListener.onRecipeClick(adapterPosition) }
    }
}