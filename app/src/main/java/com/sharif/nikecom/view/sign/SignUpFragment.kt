package com.sharif.nikecom.view.sign

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.logInButton.setOnClickListener {
            navController.navigate(R.id.sign_up_to_in)
        }
    }
}