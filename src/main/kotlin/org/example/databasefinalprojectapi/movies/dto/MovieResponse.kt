package org.example.databasefinalprojectapi.movies.dto

data class MovieResponse(
    val id: Long,
    val title: String,
    val engTitle: String?,
    val year: Int?,
    val country: String?,
    val mType: String?,
    val genre: String?,
    val status: String?,
    val director: String?,
    val company: String?,
)