package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> { LikedRestaurantsFragment.newInstance() }
            1 -> { NearbyRestaurantsFragment.newInstance()}
            2 -> { FamousRestaurantsFragment.newInstance()}
            3 -> { AllRestaurantsFragment.newInstance()}
            else -> { AllRestaurantsFragment.newInstance()}
        }

    }

}