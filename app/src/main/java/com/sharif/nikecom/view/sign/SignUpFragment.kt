package com.sharif.nikecom.view.sign

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentSignUpBinding
import com.sharif.nikecom.view.FeedActivity

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    @Deprecated("Deprecated in Java")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        navController = Navigation.findNavController(view)
        auth = Firebase.auth

        setListeners()
    }

    private fun setListeners() {
        binding.logInButton.setOnClickListener {
            navController.navigate(R.id.sign_up_to_in)
        }

        binding.signUp.setOnClickListener { signUp() }
    }

    private fun signUp() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        val displayName = binding.inputName.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (email.length > 15 && password.length > 6) {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    // Navigation
                    val intent = Intent(requireActivity(), FeedActivity::class.java)
                    startActivity(intent)

                    val userProfile = UserProfileChangeRequest.Builder()
                    userProfile.setDisplayName(displayName)

                    auth.currentUser?.updateProfile(userProfile.build())

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Size", Toast.LENGTH_LONG).show()
            }
        }
    }
}