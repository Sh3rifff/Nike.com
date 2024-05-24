package com.sharif.nikecom.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sharif.nikecom.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}