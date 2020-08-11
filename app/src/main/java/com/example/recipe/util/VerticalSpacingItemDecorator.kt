package com.example.recipe.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


//
// Created by Admin on 8/11/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

class VerticalSpacingItemDecorator(val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = verticalSpaceHeight
    }
}