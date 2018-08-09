package com.github.phzhou76.retask.handler

import android.os.Handler
import android.os.Message
import android.util.Log
import com.github.phzhou76.retask.MainActivity

class MainActivityHandler(mainActivity: MainActivity) : Handler()
{
    private val mMainActivity: MainActivity = mainActivity

    /**
     * Handles incoming Messages from the proxy service.
     */
    override fun handleMessage(message: Message?)
    {
        message?.let {
            when (it.arg1)
            {
                /* Accessibility Service not connected. */
                0    ->
                {
                    Log.d("MainActivityHandler", "Not connected.")
                    mMainActivity.mAccessibilityEnabled = false
                    mMainActivity.updateAccessibilityServiceStatus(false)
                }

                /* Accessibility Service connected. */
                else ->
                {
                    Log.d("MainActivityHandler", "Connected.")
                    mMainActivity.mAccessibilityEnabled = true
                    mMainActivity.updateAccessibilityServiceStatus(true)
                }
            }
        }
    }
}