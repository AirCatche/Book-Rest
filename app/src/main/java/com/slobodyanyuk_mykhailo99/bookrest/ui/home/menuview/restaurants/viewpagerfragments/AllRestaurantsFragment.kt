package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentTabRestaurantsAllBinding

class AllRestaurantsFragment () : Fragment() {
    private var _binding: FragmentTabRestaurantsAllBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabRestaurantsAllBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : AllRestaurantsFragment {
            val args: Bundle = Bundle()
            val fragment = AllRestaurantsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}