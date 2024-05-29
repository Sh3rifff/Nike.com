package com.sharif.nikecom.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        navigation()

    }

    private fun navigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
                ?: return
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigationView, navController)
    }
}