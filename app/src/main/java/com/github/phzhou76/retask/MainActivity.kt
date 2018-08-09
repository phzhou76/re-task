package com.github.phzhou76.retask

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.phzhou76.retask.handler.MainActivityHandler
import com.github.phzhou76.retask.model.TaskScript
import com.github.phzhou76.retask.model.statement.ClickRegionStatement
import com.github.phzhou76.retask.model.statement.WaitStatement
import com.github.phzhou76.retask.service.TaskProxyService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    companion object
    {
        private val TAG: String = MainActivity::class.java.simpleName

        const val KEY_TASK_SCRIPT: String = "KEY_TASK_SCRIPT"
        const val KEY_BUNDLE: String = "KEY_BUNDLE"
    }

    /* Accessibility service enabled. */
    var mAccessibilityEnabled = false

    /* Connected to proxy service. */
    private var mIsBound = false

    /* Reference to the proxy service's Messenger. */
    private var mTaskMessenger: Messenger? = null

    /* Used for receiving Messages back from the proxy service. */
    private val mMainActivityMessenger: Messenger = Messenger(MainActivityHandler(this))

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
                Log.d(TAG, "onServiceConnected")

                updateProxyServiceStatus(true)
                checkAccessibilityServiceConnection()
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

            /* Only send script if the Activity is bound to the proxy service. */
            if (mIsBound)
            {
                val taskScript = TaskScript()
                taskScript.mTaskScript?.let {
                    it.mStatementList.add(WaitStatement(5000, 2500))
                    it.mStatementList.add(ClickRegionStatement(Pair(100.0f, 100.0f), Pair(1000.0f, 2000.0f)))

                    val bundle = Bundle()
                    bundle.putParcelable(KEY_TASK_SCRIPT, taskScript)

                    val scriptIntent = Intent(this, TaskProxyService::class.java)
                    scriptIntent.putExtra(KEY_BUNDLE, bundle)
                    startService(scriptIntent)
                }
            }
        }
    }

    override fun onStart()
    {
        super.onStart()

        Log.d(TAG, "onStart")
        val taskIntent = Intent(this, TaskProxyService::class.java)
        bindService(taskIntent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop()
    {
        super.onStop()

        Log.d(TAG, "onStop")
        if (mIsBound)
        {
            unbindService(mServiceConnection)
            updateProxyServiceStatus(false)
            mIsBound = false
        }
    }

    override fun onResume()
    {
        super.onResume()

        Log.d(TAG, "onResume")

        /* Check status of both proxy service and Accessibility Service. */
        if (mIsBound)
        {
            checkAccessibilityServiceConnection()
        }
        else
        {
            updateProxyServiceStatus(false)
            updateAccessibilityServiceStatus(false)
        }
    }

    /**
     * Sends a Message to the proxy service to determine if the Accessibility Service
     * is enabled or not.
     */
    private fun checkAccessibilityServiceConnection()
    {
        if(mIsBound)
        {
            val checkConnectionMessage = Message.obtain()
            checkConnectionMessage.arg1 = 0
            checkConnectionMessage.replyTo = mMainActivityMessenger
            mTaskMessenger?.send(checkConnectionMessage)
        }
    }

    private fun updateProxyServiceStatus(isConnected: Boolean)
    {
        if(isConnected)
        {
            proxyServiceTextView.text = "Proxy Service: Connected"
        }
        else
        {
            proxyServiceTextView.text = "Proxy Service: Not connected"
        }
    }

    fun updateAccessibilityServiceStatus(isConnected: Boolean)
    {
        if (isConnected)
        {
            accessibilityServiceTextView.text = "Accessibility Service: Connected"
        }
        else
        {
            accessibilityServiceTextView.text = "Accessibility Service: Not connected"
        }
    }
}
