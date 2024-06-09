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
        val movieDirectors =
            movieDirectorRepository.findAllByMovieDirectorIdDirectorIdIn(directorIds)
        val directorMap = mutableMapOf<Long, MutableList<Director>>()

        movieDirectors.forEach {
            val director =
                directors.find { director -> director.id == it.movieDirectorId.directorId }
            if (director != null) {
                if (directorMap[it.movieDirectorId.movieId] == null) {
                    directorMap[it.movieDirectorId.movieId] = mutableListOf(director)
                } else {
                    directorMap[it.movieDirectorId.movieId]?.add(director)
                }
            }
        }

        return directorMap
    }
}