package com.slobodyanyuk_mykhailo99.bookrest.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Restaurant
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Review
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(), LifecycleObserver {

    fun createTestDataRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant(1,"Mafia", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),),
                listOf("https://i.ibb.co/tPyBRZN/wide-angle-shot-of-an-Empty-restaurant.jpg", "https://ibb.co/DVwgLhL"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),

            Restaurant(2,"Sushiya", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","4.8", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),),
                listOf("https://i.ibb.co/mNwWh06/Upscale-Fine-Dining-Italian-Restaurant-Dubai-An-Upscale-Fine-Dining-Italian-Restaurant-in-Dubai.jpg", "https://ibb.co/GHDSxst"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),

            Restaurant(3,"Puzata Khata", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","3.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),),
                listOf("https://i.ibb.co/bvhxPQ0/test3.jpg", "https://ibb.co/GHDSxst"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),
            Restaurant(4,"EURAZIA", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","2.6", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),),
                listOf("https://i.ibb.co/gPBnSbC/test4.jpg", "https://ibb.co/GHDSxst"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),
            Restaurant(5,"Tut budea", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","5.0", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58")),
                listOf("https://i.ibb.co/ft1XtB0/test5.jpg", "https://ibb.co/GHDSxst"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),
            Restaurant(6,"Tut Burger", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","5.0", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58")),
                listOf("https://i.ibb.co/sqKM7JD/test6.jpg", "https://ibb.co/GHDSxst"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),
            Restaurant(7,"Tut Sonbka", "https://mafia.com","+38-063-89-78-892", "Razina 12, Peremogu avenue",
                listOf("Chill", "Sushi", "Pizza", "huge place", "delivery"),"false",
                listOf(Review(1,"Mishka","someUrl","5.0", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58"),
                    Review(1,"Mishka","someUrl","4.2", "Very delicious tea","05.08.2021, 14:58")),
                listOf("https://i.ibb.co/Yb8VKRV/test7.jpg", "https://ibb.co/GHDSxst"),
                listOf("MenuPhoto"), "Fantasy place for family and party for youth","Some schedule","Ukrainian"),
        )
    }

}