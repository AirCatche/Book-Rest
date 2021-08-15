package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentMenuProfileBinding

class ProfileFragment: Fragment() {
    private var _binding: FragmentMenuProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMenuProfileBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : ProfileFragment{
            val args: Bundle = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

}