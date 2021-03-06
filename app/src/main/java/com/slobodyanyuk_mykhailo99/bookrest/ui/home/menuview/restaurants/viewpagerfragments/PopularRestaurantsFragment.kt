package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentTabRestaurantsPopularBinding

class PopularRestaurantsFragment : Fragment() {
    private var _binding: FragmentTabRestaurantsPopularBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabRestaurantsPopularBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : PopularRestaurantsFragment {
            val args: Bundle = Bundle()
            val fragment = PopularRestaurantsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}