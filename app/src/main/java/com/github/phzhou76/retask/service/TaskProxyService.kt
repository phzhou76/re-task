package com.github.phzhou76.retask.service

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import android.util.Log
import com.github.phzhou76.retask.MainActivity
import com.github.phzhou76.retask.model.statement.StatementBlock

class TaskProxyService : IntentService("TaskProxyService")
{
    private var scriptTask: StatementBlock? = null

    override fun onHandleIntent(intent: Intent?)
    {
        TaskAccessibilityService.getSharedInstance()?.bindTaskProxyService(this)
        Log.d("TaskProxyService", "onHandleIntent")

        intent?.let {
            val bundle: Bundle = it.getBundleExtra(MainActivity.KEY_BUNDLE)
            bundle.classLoader = StatementBlock::class.java.classLoader

            val statementBlock: StatementBlock? = bundle.getParcelable(MainActivity.KEY_SCRIPT)
            if (statementBlock != null)
            {
                scriptTask = statementBlock
                statementBlock.execute()
            }
        }

        Log.d("TaskProxyService", "Finished script.")

        TaskAccessibilityService.getSharedInstance()?.unbindTaskProxyService()
    }

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

    fun stopExecution()
    {
        scriptTask?.stopExecution()
    }
}