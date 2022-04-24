package com.a5k.moviereviews.data.model

data class DataModel (
    val has_more:String,
    val results: List<Movies>
        )

data class Movies(
    val display_title: String,
    val summary_short:String,
    val multimedia: Multimedia
)
data class Multimedia(
    val src:String
)