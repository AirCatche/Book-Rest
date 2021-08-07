package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

data class Review(
    var id: Int? = null,
    var authorName: String? = null,
    var authorPhoto: String? = null,     //url
    var estimation: String? = null,     // 1,2,3,4,5
    var description: String? = null,
    var date: String? = null,             //05.08.2021, 14:58
)
