package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Restaurant
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentTabRestaurantsAllBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.HomeViewModel
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.RestaurantAdapter

class AllRestaurantsFragment : Fragment() {
    private var _binding: FragmentTabRestaurantsAllBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabRestaurantsAllBinding.inflate(inflater,container,false)
        val restaurants = viewModel.createTestDataRestaurants()
        val adapter = RestaurantAdapter(restaurants)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentRestaurantAll.layoutManager = layoutManager
        binding.rvFragmentRestaurantAll.adapter = adapter


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(restaurants: List<Restaurant>) : AllRestaurantsFragment {
            val args: Bundle = Bundle()
            val fragment = AllRestaurantsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}