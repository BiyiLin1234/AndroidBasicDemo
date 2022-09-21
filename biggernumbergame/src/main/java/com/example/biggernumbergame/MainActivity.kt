package com.example.biggernumbergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.left_button).setOnClickListener(View.OnClickListener {
            leftButtonClick(it)
        })
        findViewById<Button>(R.id.right_button).setOnClickListener(View.OnClickListener {
            rightButtonClick(it)
        })
    }

    fun leftButtonClick(view: View) {
        left_button.
    }

    fun rightButtonClick(view: View) {

    }
}