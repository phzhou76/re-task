package com.github.phzhou76.retask

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.phzhou76.retask.model.ClickTask
import com.github.phzhou76.retask.model.ITask
import com.github.phzhou76.retask.model.WaitTask
import com.github.phzhou76.retask.service.TaskProxyService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private var mIsBound = false
    private var mTaskMessenger: Messenger? = null
    private val mServiceConnection: ServiceConnection = object : ServiceConnection
    {
        /**
         * Called when the client successfully connects to the service.
         *
         * @param componentName The component name of the service that has been
         *      connected.
         * @param binder The binder that was returned by the service on successful
         *      connection.
         */
        override fun onServiceConnected(componentName: ComponentName?, binder: IBinder?)
        {
            binder?.let {
                mTaskMessenger = Messenger(binder)
                mIsBound = true
            }
        }

        /**
         * Called when the client unexpectedly disconnects from the service.
         *
         * @param componentName The component name of the service that the client
         *      unexpectedly disconnected from.
         */
        override fun onServiceDisconnected(componentName: ComponentName?)
        {
            mIsBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if (mIsBound)
            {
                val taskList: Array<ITask> = arrayOf(WaitTask("WaitTask", 50000),
                        ClickTask("ClickTask", 1000.0f, 1000.0f))

                val taskIter = taskList.iterator()

                taskIter.forEach {
                    Log.d("WHACK", it.mTaskTitle)
                }

                val message = Message.obtain()
                val bundle = Bundle()
                bundle.putParcelableArray("TEST", taskList)

                message.obj = bundle

                try
                {
                    mTaskMessenger?.send(message)
                }
                catch (e: RemoteException)
                {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStart()
    {
        super.onStart()

        val taskIntent = Intent(this, TaskProxyService::class.java)
        bindService(taskIntent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop()
    {
        super.onStop()

        if (mIsBound)
        {
            unbindService(mServiceConnection)
            mIsBound = false
        }
    }

    override fun onResume()
    {
        super.onResume()
        checkConnection()

    }

    private fun checkConnection()
    {

    }
}
