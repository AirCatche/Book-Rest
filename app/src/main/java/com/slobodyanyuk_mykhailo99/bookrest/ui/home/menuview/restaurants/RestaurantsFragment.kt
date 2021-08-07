package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentMenuRestaurantBinding

class RestaurantsFragment: Fragment() {
    private var _binding: FragmentMenuRestaurantBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
         _binding = FragmentMenuRestaurantBinding.inflate(inflater,container,false)

        val adapter = PagerAdapter(parentFragmentManager,lifecycle)

        binding.vpRestaurant.adapter = adapter

        binding.tabLayoutRestaurantSection.apply {
            addTab(binding.tabLayoutRestaurantSection.newTab().setText("All"))
            addTab(binding.tabLayoutRestaurantSection.newTab().setText("Nearby"))
            addTab(binding.tabLayoutRestaurantSection.newTab().setText("Popular"))
            addTab(binding.tabLayoutRestaurantSection.newTab().setText("Liked"))
        }

        binding.tabLayoutRestaurantSection.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpRestaurant.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.vpRestaurant.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayoutRestaurantSection.selectTab(binding.tabLayoutRestaurantSection.getTabAt(position))
            }
        })
//        TabLayoutMediator(binding.tabLayoutRestaurantSection, binding.vpRestaurant) {tab, position ->
//            when(position) {
//                0 -> { tab.text = "ALL" }
//                1 -> { tab.text = "NEARBY"}
//                2 -> { tab.text = "POPULAR"}
//                3 -> { tab.text = "LIKED"}
//            }
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() : RestaurantsFragment {
            val args: Bundle = Bundle()
            val fragment = RestaurantsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}