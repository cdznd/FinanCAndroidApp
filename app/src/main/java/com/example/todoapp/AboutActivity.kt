package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        //Set Arrow-to-back in toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}