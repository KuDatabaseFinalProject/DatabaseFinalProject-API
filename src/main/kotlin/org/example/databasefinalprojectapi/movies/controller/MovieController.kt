package org.example.databasefinalprojectapi.movies.controller

import org.example.databasefinalprojectapi.movies.service.MovieService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(
    private val movieService: MovieService
) {
    @GetMapping("/movies")
    fun getMovies(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) director: String?,
        @RequestParam(required = false) startDate: Int?,
        @RequestParam(required = false) endDate: Int?,
        @RequestParam(required = false) sort: Int?
    ) = movieService.findAllByCondition(
        title, director, startDate, endDate, sort, page, size
    )
}