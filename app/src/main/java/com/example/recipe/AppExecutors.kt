package com.example.recipe

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService


//
// Created by Admin on 8/11/2020.
// Copyright (c) {2020} EMatrix All rights reserved.
//

object AppExecutors {
    private val mNetworkIO = Executors.newScheduledThreadPool(3)

    fun networkIO(): ScheduledExecutorService {
        return mNetworkIO
    }
}