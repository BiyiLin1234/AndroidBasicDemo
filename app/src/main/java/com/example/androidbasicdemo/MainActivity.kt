package com.example.androidbasicdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }
    // Activity创建
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // click to switch greet content and greet target.
        findViewById<TextView>(R.id.greet_tv).setOnClickListener {
            (it as? TextView)?.let { tv ->
                // Android Log Util
                Log.d(TAG, "greet_tv onclick")
                if (Objects.equals(tv.text, resources.getString(R.string.greet))) {
                    tv.text = resources.getString(R.string.default_user_name)
                } else {
                    tv.text = resources.getString(R.string.greet)
                }
            }
        }
    }
}