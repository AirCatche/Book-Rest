package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Restaurant
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Review
import com.slobodyanyuk_mykhailo99.bookrest.databinding.FragmentMenuRestaurantBinding

class RestaurantsFragment: Fragment() {
    private var _binding: FragmentMenuRestaurantBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurants: List<Restaurant>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
         _binding = FragmentMenuRestaurantBinding.inflate(inflater,container,false)
        restaurants = createTestData()
        setupViewPager()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createTestData(): List<Restaurant> {
        return listOf(
            Restaurant(1,"Mafia", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58")),
                listOf("First Photo", "Second photo"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),

            Restaurant(2,"Sushiya", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58")),
                listOf("First Photo", "Second photo"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),

            Restaurant(3,"Puzata Khata", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58")),
                listOf("First Photo", "Second photo"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),
        )
    }

    private fun setupViewPager() {
        val adapter = PagerAdapter(parentFragmentManager,lifecycle, restaurants)
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