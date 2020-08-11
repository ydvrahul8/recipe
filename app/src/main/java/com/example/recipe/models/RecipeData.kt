package com.example.recipe.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecipeData : Parcelable {
    val _id: String? = null
    val image_url: String? = null
    val publisher: String? = null
    val publisher_url: String? = null
    val recipe_id: String? = null
    val social_rank: Double? = null
    val source_url: String? = null
    var title: String? = null
}