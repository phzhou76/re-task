package com.github.phzhou76.retask.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Messenger

class TaskProxyService : Service()
{
    /* Messenger that the client Activity sends Messages to. */
    private val mMessenger: Messenger = Messenger(TaskProxyHandler(this))

    /**
     * This method is called when a client binds to this service.
     *
     * @param intent The Intent that the client sent to this service.
     *
     * @return An IBinder-implementing object that the client can use to communicate
     *      with the service.
     */
    override fun onBind(intent: Intent?): IBinder
    {
        return mMessenger.binder
    }
}