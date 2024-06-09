package org.example.databasefinalprojectapi.genre.service

import org.example.databasefinalprojectapi.genre.repository.GenreRepository
import org.springframework.stereotype.Service

@Service
class GenreService(
    private val genreRepository: GenreRepository
) {
    fun getGenreMap(movieIds: List<Long>): Map<Long, String> {
        if (movieIds.isEmpty()) return emptyMap()

        val genres = genreRepository.findAllByGenreIdMovieIdIn(movieIds)
        val genreMap = genres
            .filterNotNull()
            .groupBy({ it.genreId.movieId }, { it.genreId.genre })
            .mapValues { it.value.joinToString() }

        return genreMap
    }
}