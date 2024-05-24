package com.sharif.nikecom.view.sign

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)
        navController = Navigation.findNavController(view)

        auth = Firebase.auth

        binding.resetButton.setOnClickListener {
            navController.navigate(R.id.forgot_to_otp)
            resetPassword()
        }

    }

    private fun resetPassword() {
        val emailAddress = binding.inputEmail.text.toString()

        Firebase.auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Email sending", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(requireContext(), "Email can't send", Toast.LENGTH_LONG).show()

                }
            }
    }
}