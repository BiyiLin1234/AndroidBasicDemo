package com.example.androidbasicdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SingleInstanceActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SingleInstanceActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)
        Log.d(TAG, this.toString())
        findViewById<Button>(R.id.open_self_btn).setOnClickListener { view ->
            startActivity(Intent(this, SingleInstanceActivity::class.java))
        }
    }
}