package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentRestaurantBinding

class RestaurantsFragment: Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRestaurantBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() : RestaurantsFragment{
            val args: Bundle = Bundle()
            val fragment = RestaurantsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}