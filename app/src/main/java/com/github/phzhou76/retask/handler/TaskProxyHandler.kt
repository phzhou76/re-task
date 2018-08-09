package com.github.phzhou76.retask.handler

import android.os.Handler
import android.os.Message
import com.github.phzhou76.retask.service.TaskProxyService

class TaskProxyHandler(taskProxyService: TaskProxyService) : Handler()
{
    private val mTaskProxyService: TaskProxyService = taskProxyService

    /**
     * Handles incoming Messages from client Activities.
     *
     * @param message The Message to handle.
     */
    override fun handleMessage(message: Message?)
    {
        message?.let {
            when (it.arg1)
            {
                0 ->
                {
                    val isConnected = if (mTaskProxyService.isAccessibilityConnected()) 1 else 0

                    val replyMessage = Message.obtain()
                    replyMessage.arg1 = isConnected

                    it.replyTo.send(replyMessage)
                }
            }
        }
    }
}