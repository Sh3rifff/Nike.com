package com.sharif.nikecom.view.sign

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentSplashBinding

@Suppress("DEPRECATION")
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        navController = Navigation.findNavController(view)


        Handler().postDelayed({
            navController.navigate(R.id.splash_to_on1)
        }, 1000)

    }
}