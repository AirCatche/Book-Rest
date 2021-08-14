package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Restaurant
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments.AllRestaurantsFragment
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments.PopularRestaurantsFragment
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments.LikedRestaurantsFragment
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments.NearbyRestaurantsFragment

class PagerAdapter(fragmentActivity: FragmentManager, lifecycle: Lifecycle, private val restaurants: List<Restaurant>) : FragmentStateAdapter(fragmentActivity,lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> { AllRestaurantsFragment.newInstance(restaurants) }
            1 -> { NearbyRestaurantsFragment.newInstance()}
            2 -> { PopularRestaurantsFragment.newInstance()}
            3 -> { LikedRestaurantsFragment.newInstance()}
            else -> { AllRestaurantsFragment.newInstance(restaurants)}
        }
    }
}