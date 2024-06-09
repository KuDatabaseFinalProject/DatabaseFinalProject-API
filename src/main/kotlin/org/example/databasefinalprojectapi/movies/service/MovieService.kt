package org.example.databasefinalprojectapi.movies.service

import org.example.databasefinalprojectapi.directors.service.DirectorService
import org.example.databasefinalprojectapi.genre.service.GenreService
import org.example.databasefinalprojectapi.movies.dto.MovieResponse
import org.example.databasefinalprojectapi.movies.enum.MovieSort
import org.example.databasefinalprojectapi.movies.repository.CustomMovieRepository
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val customMovieRepository: CustomMovieRepository,
    private val directorService: DirectorService,
    private val genreService: GenreService
) {
    fun findAllByCondition(
        title: String?,
        director: String?,
        startDate: Int?,
        endDate: Int?,
        sort: Int?,
        page: Int,
        size: Int
    ): PageImpl<MovieResponse> {
        val parsedSort = sort?.run { MovieSort.entries.find { it.sort == sort } }
        val pageRequest = PageRequest.of(page, size)

        val directorMap = directorService.getDirectorMap(director)
        val movies = customMovieRepository.findAllByCondition(
            directorMap.keys.toList(),
            title,
            startDate,
            endDate,
            parsedSort,
            pageRequest
        )
        val genreMap = genreService.getGenreMap(movies.map { it.id })

        val count = customMovieRepository.countAllByCondition(
            directorMap.keys.toList(),
            title,
            startDate,
            endDate
        )

        return movies.map {
            MovieResponse(
                it.id,
                it.title,
                it.engTitle,
                it.year,
                it.country,
                it.mType,
                genreMap[it.id],
                it.status,
                directorMap[it.id]?.joinToString { director -> director.name } ?: "",
                it.company
            )
        }.let {
            PageImpl(it, pageRequest, count)
        }
    }
}