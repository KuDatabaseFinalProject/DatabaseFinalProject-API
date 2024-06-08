package org.example.databasefinalprojectapi.movies.service

import org.example.databasefinalprojectapi.movies.entity.Movie
import org.example.databasefinalprojectapi.movies.enum.MovieSort
import org.example.databasefinalprojectapi.movies.repository.CustomMovieRepository
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val customMovieRepository: CustomMovieRepository
) {
    fun findAllByCondition(
        title: String?,
        director: String?,
        startDate: Int?,
        endDate: Int?,
        sort: Int?,
        page: Int,
        size: Int
    ): PageImpl<Movie> {
        val parsedSort = sort?.run { MovieSort.entries.find { it.sort == sort } }

        return customMovieRepository.findAllByCondition(
            title,
            director,
            startDate,
            endDate,
            parsedSort,
            PageRequest.of(page, size)
        )
    }
}