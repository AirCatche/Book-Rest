package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentRestaurantsFamousBinding

class FamousRestaurantsFragment : Fragment() {
    private var _binding: FragmentRestaurantsFamousBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRestaurantsFamousBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : FamousRestaurantsFragment {
            val args: Bundle = Bundle()
            val fragment = FamousRestaurantsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}