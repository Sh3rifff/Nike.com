package com.sharif.nikecom.view.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sharif.nikecom.R
import com.sharif.nikecom.adapter.PopularAdapter
import com.sharif.nikecom.databinding.FragmentFavBinding
import com.sharif.nikecom.model.ProductModel

class FavFragment : Fragment(R.layout.fragment_fav) {

    private lateinit var binding: FragmentFavBinding
    private lateinit var favAdapter: PopularAdapter
    private lateinit var favList: ArrayList<ProductModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavBinding.bind(view)

        favList = ArrayList()

        binding.favouriteRecycler.layoutManager =
            GridLayoutManager(requireContext(), GridLayoutManager.DEFAULT_SPAN_COUNT)
        favAdapter = PopularAdapter(favList)
        binding.favouriteRecycler.adapter = favAdapter


    }

}