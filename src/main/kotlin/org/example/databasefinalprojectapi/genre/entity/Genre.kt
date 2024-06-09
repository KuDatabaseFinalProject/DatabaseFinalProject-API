package org.example.databasefinalprojectapi.genre.entity

import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "genre")
class Genre(
    @EmbeddedId
    val genreId: GenreId
)

@Embeddable
class GenreId(
    val movieId: Long,
    val genre: String,
)