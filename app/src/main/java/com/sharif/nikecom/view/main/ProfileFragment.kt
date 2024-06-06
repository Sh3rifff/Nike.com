package com.sharif.nikecom.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.sharif.nikecom.R
import com.sharif.nikecom.databinding.FragmentProfileBinding
import com.sharif.nikecom.view.MainActivity
import java.util.UUID

@Suppress("DEPRECATION")
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private var selectedPicture: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        auth = Firebase.auth
        storage = Firebase.storage

        signOut()
        imagePick()
        uploadPP()

    }

    private fun uploadPP() {
        val uuid = UUID.randomUUID()
        val imageName = "$uuid.jpg"
        val reference = storage.reference
        val imageReference = reference.child("profile").child(imageName)

        if (selectedPicture != null) {
            imageReference.putFile(selectedPicture!!).addOnSuccessListener {

                val uploadPictureReference = storage.reference.child("images").child(imageName)
                uploadPictureReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()

                    val postMap = hashMapOf<String, Any>()
                    postMap["downloadUrl"] = downloadUrl
                    postMap["userEmail"] = auth.currentUser!!.email!!
                    postMap["time"] = Timestamp.now()
                }
            }
        }
    }

    private fun imagePick() {
        binding.profileImage.setOnClickListener {

            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
    }

    private fun signOut() {
        binding.signOut.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.profileImage.setImageURI(data?.data)
        selectedPicture = data?.data

    }
}