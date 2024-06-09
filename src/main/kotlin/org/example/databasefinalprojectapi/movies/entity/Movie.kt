package org.example.databasefinalprojectapi.movies.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "movie")
class Movie(
    @Id
    val id: Long,
    val title: String,
    val engTitle: String?,
    val year: Int?,
    val country: String?,
    val mType: String?,
    val status: String?,
    val company: String?,
)