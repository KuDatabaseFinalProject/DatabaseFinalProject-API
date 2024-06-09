package org.example.databasefinalprojectapi.directors.entity

import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "movie_director")
class MovieDirector(
    @EmbeddedId
    val movieDirectorId: MovieDirectorId,
)

@Embeddable
class MovieDirectorId(
    val movieId: Long,
    val directorId: Long,
)