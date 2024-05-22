package com.sharif.nikecom.view.sign

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentOnBoard3Binding

class OnBoard3Fragment : Fragment(R.layout.fragment_on_board3) {

    private lateinit var binding: FragmentOnBoard3Binding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnBoard3Binding.bind(view)
        navController = Navigation.findNavController(view)

        binding.next.setOnClickListener {
            navController.navigate(R.id.on3_to_sign_in)
        }


    }
}