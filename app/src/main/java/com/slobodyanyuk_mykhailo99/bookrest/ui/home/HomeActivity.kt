package com.slobodyanyuk_mykhailo99.bookrest.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivityHomeBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.AboutFragment
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.ProfileFragment
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.RestaurantsFragment
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.SettingFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        var selectedFragment = Fragment()
        when (item.itemId) {
            R.id.nav_restaurants -> {
                selectedFragment = RestaurantsFragment.newInstance()
            }
            R.id.nav_profile -> {
                selectedFragment = ProfileFragment.newInstance()
            }
            R.id.nav_settings -> {
                selectedFragment = SettingFragment.newInstance()
            }
            R.id.nav_about -> {
                selectedFragment = AboutFragment.newInstance()
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(binding.includeContentHome.mainFragmentContainer.id, selectedFragment).commit()
        return@OnItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Create specific bottom navigation + set menu item listener
        binding.bottomNavigation.apply {
            background = null
            itemTextAppearanceActive = 0
            setOnItemSelectedListener(navListener)
        }

        selectFragmentRestaurant()
    }

    private fun selectFragmentRestaurant() {
        supportFragmentManager.beginTransaction()
            .replace(binding.includeContentHome.mainFragmentContainer.id, RestaurantsFragment.newInstance()).commit()
        binding.bottomNavigation.selectedItemId = R.id.nav_restaurants
    }

    companion object {
        private const val TAG = "HomeActivity"
    }

}