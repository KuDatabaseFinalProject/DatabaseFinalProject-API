package org.example.databasefinalprojectapi.genre.repository

import org.example.databasefinalprojectapi.genre.entity.Genre
import org.example.databasefinalprojectapi.genre.entity.GenreId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository : JpaRepository<Genre, GenreId> {
    fun findAllByGenreIdMovieIdIn(movieIds: List<Long>): List<Genre?>
}