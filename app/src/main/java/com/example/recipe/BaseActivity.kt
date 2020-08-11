package com.example.recipe

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


//
// Created by Admin on 8/8/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

abstract class BaseActivity : AppCompatActivity() {
    public var mProgressBar: ProgressBar? = null
    override fun setContentView(layoutResID: Int) {
        val constraintLayout: ConstraintLayout =
            layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        val frameLayout: FrameLayout = constraintLayout.findViewById(R.id.activity_contemt)
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar)
        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(constraintLayout)
    }

    fun showProgressBar(visibility: Boolean) {
        if (visibility)
            mProgressBar?.visibility = View.VISIBLE
        else
            mProgressBar?.visibility = View.INVISIBLE
    }
}