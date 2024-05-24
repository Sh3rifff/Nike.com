@file:Suppress("DEPRECATION")

package com.sharif.nikecom.view.sign

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentSignInBinding
import com.sharif.nikecom.view.FeedActivity

@Suppress("DEPRECATION")
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient


    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        navController = Navigation.findNavController(view)
        auth = Firebase.auth

        setListeners()

    }

    private fun setListeners() {
        binding.newUserButton.setOnClickListener {
            navController.navigate(R.id.sign_in_to_up)
        }

        binding.recoveryPasswordButton.setOnClickListener {
            navController.navigate(R.id.sign_in_to_forgot)
        }

        binding.signInButton.setOnClickListener { signIn() }

        binding.googleSign.setOnClickListener {
            signInWithGoogle()
        }

//        rememberMe()
    }



    private fun signIn() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                // Navigation
                val intent = Intent(requireActivity(), FeedActivity::class.java)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun rememberMe() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(requireActivity(), FeedActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(), currentUser.displayName, Toast.LENGTH_LONG).show()
        }
    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(
                    requireContext(),
                    "failed: ${e.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(
                        requireContext(),
                        "Signed in as ${user?.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(requireContext(), FeedActivity::class.java))
                } else {
                    Toast.makeText(requireContext(), "Authentication failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}