package com.github.phzhou76.retask.service

import android.os.Handler
import android.os.Message

class TaskProxyHandler(taskProxyService: TaskProxyService) : Handler()
{
    private val mTaskProxyService: TaskProxyService = taskProxyService

    override fun handleMessage(message: Message?)
    {
        super.handleMessage(message)
    }
}