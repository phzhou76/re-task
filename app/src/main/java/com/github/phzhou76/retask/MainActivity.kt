package com.github.phzhou76.retask

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import android.support.v7.app.AppCompatActivity
import com.github.phzhou76.retask.model.statement.ClickRegionStatement
import com.github.phzhou76.retask.model.statement.StatementBlock
import com.github.phzhou76.retask.model.statement.WaitStatement
import com.github.phzhou76.retask.service.TaskProxyService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    companion object
    {
        const val KEY_SCRIPT: String = "KEY_SCRIPT"
        const val KEY_BUNDLE: String = "KEY_BUNDLE"
    }

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

            /* Only send script if the Activity is bound to the proxy service. */
            if (mIsBound)
            {
                val statementBlock = StatementBlock()
                statementBlock.mStatementList.add(WaitStatement(5000, 2500))
                statementBlock.mStatementList.add(ClickRegionStatement(Pair(100.0f, 100.0f), Pair(1000.0f, 2000.0f)))

                val bundle = Bundle()
                bundle.putParcelable(KEY_SCRIPT, statementBlock)

                val scriptIntent = Intent(this, TaskProxyService::class.java)
                scriptIntent.putExtra(KEY_BUNDLE, bundle)
                startService(scriptIntent)
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
