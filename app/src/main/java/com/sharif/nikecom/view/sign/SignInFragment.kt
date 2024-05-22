package com.sharif.nikecom.view.sign

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentOnBoard3Binding
import com.sharif.nikecom.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.newUserButton.setOnClickListener {
            navController.navigate(R.id.sign_in_to_up)
        }

        binding.recoveryPasswordButton.setOnClickListener {
            navController.navigate(R.id.sign_in_to_forgot)
        }


    }

}