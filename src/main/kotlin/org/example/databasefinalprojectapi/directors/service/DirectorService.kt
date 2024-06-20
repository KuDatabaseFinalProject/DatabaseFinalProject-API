package org.example.databasefinalprojectapi.directors.service

import org.example.databasefinalprojectapi.directors.entity.Director
import org.example.databasefinalprojectapi.directors.repository.DirectorRepository
import org.example.databasefinalprojectapi.directors.repository.MovieDirectorRepository
import org.springframework.stereotype.Service

@Service
class DirectorService(
    private val directorRepository: DirectorRepository,
    private val movieDirectorRepository: MovieDirectorRepository
) {
    fun getDirectorMap(directorName: String?): Map<Long, List<Director>> {
        if (directorName == null) return emptyMap()

        val directors = directorRepository.findAllByNameContains(directorName)
        val directorIds = directors.map { it.id }
        val directorsMap = directors.associateBy { it.id }
        val movieDirectors =
            movieDirectorRepository.findAllByMovieDirectorIdDirectorIdIn(directorIds)
        val directorMap = movieDirectors
            .groupBy(
                { it.movieDirectorId.movieId },
                { directorsMap[it.movieDirectorId.directorId]!! })

        return directorMap
    }
}