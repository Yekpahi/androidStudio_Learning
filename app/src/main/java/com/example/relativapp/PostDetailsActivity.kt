package com.example.relativapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PostDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val title = intent.getStringExtra("title")
        tvTitle.text= title

        supportActionBar!!.title = title
    }
}