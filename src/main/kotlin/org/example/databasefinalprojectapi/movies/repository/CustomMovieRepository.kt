package org.example.databasefinalprojectapi.movies.repository

import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.example.databasefinalprojectapi.movies.entity.Movie
import org.example.databasefinalprojectapi.movies.entity.QMovie
import org.example.databasefinalprojectapi.movies.enum.MovieSort
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class CustomMovieRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun findAllByCondition(
        movieIds: List<Long>,
        title: String?,
        startDate: Int?,
        endDate: Int?,
        sort: MovieSort?,
        pageRequest: PageRequest
    ): MutableList<Movie> {
        val query = getFindAllByConditionQuery(movieIds, title, startDate, endDate, sort)

        return query.offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()
    }

    fun countAllByCondition(
        movieIds: List<Long>,
        title: String?,
        startDate: Int?,
        endDate: Int?
    ): Long {
        val query = getFindAllByConditionQuery(movieIds, title, startDate, endDate, null)

        return query.fetch().size.toLong()
    }

    private fun getFindAllByConditionQuery(
        movieIds: List<Long>,
        title: String?,
        startDate: Int?,
        endDate: Int?,
        sort: MovieSort?
    ): JPAQuery<Movie> {
        val movie = QMovie.movie
        val query = queryFactory.selectFrom(movie)

        if (movieIds.isNotEmpty()) query.where(movie.id.`in`(movieIds))
        if (title != null) query.where(movie.title.contains(title))
        if (startDate != null) query.where(movie.year.goe(startDate))
        if (endDate != null) query.where(movie.year.loe(endDate))
        if (sort != null) {
            when (sort) {
                MovieSort.YEAR -> query.orderBy(movie.year.asc())
                MovieSort.TITLE -> query.orderBy(movie.title.asc())
            }
        }

        return query
    }
}