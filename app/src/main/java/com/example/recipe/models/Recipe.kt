package com.example.recipe.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val count: Int,
    val recipes: List<RecipeData>,
    val recipe: RecipeData
):Parcelable