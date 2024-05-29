package com.sharif.nikecom.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.sharif.nikecom.R
import com.sharif.nikecom.adapter.CategoryAdapter
import com.sharif.nikecom.adapter.PopularAdapter
import com.sharif.nikecom.databinding.FragmentHomeBinding
import com.sharif.nikecom.model.CategoryModel
import com.sharif.nikecom.model.ProductModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var productList: ArrayList<ProductModel>
    private lateinit var categoryList: ArrayList<CategoryModel>
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var firestore: FirebaseFirestore
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        auth = Firebase.auth
        storage = Firebase.storage
        firestore = Firebase.firestore


        val cat1 = CategoryModel("All Shoes")
        val cat2 = CategoryModel("Outdoor")
        val cat3 = CategoryModel("Tennis")
        val cat4 = CategoryModel("Football")
        val cat5 = CategoryModel("Classic")

        val data = arrayListOf(cat1, cat2, cat3, cat4, cat5)

        productList = ArrayList()
        categoryList = ArrayList(data)

        binding.recyclerPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        popularAdapter = PopularAdapter(productList)
        binding.recyclerPopular.adapter = popularAdapter

        binding.categoryRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryAdapter(categoryList)
        binding.categoryRecycler.adapter = categoryAdapter

        getData()
        categoryColor()

    }

    private fun categoryColor() {


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        firestore.collection("123").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
                Log.d("TAG", error.localizedMessage!!)
            } else {

                if (value != null) {
                    if (!value.isEmpty) {
                        val documents = value.documents

                        productList.clear()

                        for (document in documents) {
                            val image = document.get("image") as String
                            val name = document.get("name") as String
                            val price = document.get("price") as String

                            val post = ProductModel(image, name, price)

                            productList.add(post)
                        }

                        popularAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}