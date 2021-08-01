package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentRestaurantsLikedBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.AboutFragment

class LikedRestaurantsFragment : Fragment() {
    private var _binding: FragmentRestaurantsLikedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRestaurantsLikedBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance() : LikedRestaurantsFragment {
            val args: Bundle = Bundle()
            val fragment = LikedRestaurantsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}