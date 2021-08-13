package com.slobodyanyuk_mykhailo99.bookrest.ui.home.menuview.restaurants.viewpagerfragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Restaurant

class RestaurantAdapter (private val restaurants: List<Restaurant>) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.rv_item_restaurant,parent,false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = restaurants[position]
        holder.name.text = currentRestaurant.name
        holder.description.text = currentRestaurant.description
        holder.quantityReviews.text = "(${currentRestaurant.reviews?.size})"
        holder.like.isPressed = currentRestaurant.isLiked == "true"
        holder.rating.rating = currentRestaurant.reviews?.first()?.estimation?.toFloat() ?: 0f

    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_restaurant_item_name)
        val quantityReviews: TextView = itemView.findViewById(R.id.tv_restaurant_item_quantity_review)
        val photo: ImageView = itemView.findViewById(R.id.iv_restaurant_item_bg)
        val like: ImageView = itemView.findViewById(R.id.iv_restaurant_item_like)
        val description: TextView = itemView.findViewById(R.id.tv_restaurant_item_description)
        val rating: RatingBar = itemView.findViewById(R.id.rb_restaurant_item_star)
    }

}