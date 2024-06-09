package org.example.databasefinalprojectapi.directors.repository

import org.example.databasefinalprojectapi.directors.entity.MovieDirector
import org.example.databasefinalprojectapi.directors.entity.MovieDirectorId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieDirectorRepository : JpaRepository<MovieDirector, MovieDirectorId> {
    fun findAllByMovieDirectorIdDirectorIdIn(movieId: List<Long>): List<MovieDirector>
}