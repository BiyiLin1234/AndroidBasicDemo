package com.example.ch6_broadcast_demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        val staticHandler = Handler()
    }
    val handler = Handler()
    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        println(handler.toString())
        println(staticHandler.toString())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentFilter = IntentFilter()
        // system broadcast list: <Android SDK>/platform/<任意API版本>/data/broadcast_actions.txt
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Thread.sleep(10000)

            val h = HandlerThread("myHT")
            h.start()
            val handler = Handler(h.looper)
            handler.postDelayed({
                Log.e(TAG, "10000 delay")
            }, 10000)
            Toast.makeText(context, "time has changed", Toast.LENGTH_LONG).show()


            Log.i(TAG, "time has changed")
        }
    }
}