package org.example.databasefinalprojectapi.movies.repository

import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.example.databasefinalprojectapi.movies.entity.Movie
import org.example.databasefinalprojectapi.movies.entity.QMovie
import org.example.databasefinalprojectapi.movies.enum.MovieSort
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class CustomMovieRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun findAllByCondition(
        title: String?,
        director: String?,
        startDate: Int?,
        endDate: Int?,
        sort: MovieSort?,
        pageRequest: PageRequest
    ): PageImpl<Movie> {
        val query = getFindAllByConditionQuery(title, director, startDate, endDate, sort)

        val count = query.fetch().size.toLong()
        val contents = query.offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()
        return PageImpl(contents, pageRequest, count)
    }

    private fun getFindAllByConditionQuery(
        title: String?,
        director: String?,
        startDate: Int?,
        endDate: Int?,
        sort: MovieSort?
    ): JPAQuery<Movie> {
        val movie = QMovie.movie
        val query = queryFactory.selectFrom(movie)

        if (title != null) query.where(movie.title.contains(title))
        if (director != null) query.where(movie.director.contains(director))
        if (startDate != null) query.where(
            movie.year.goe(startDate)
        )
        if (endDate != null) query.where(
            movie.year.loe(endDate)
        )

        if (sort != null) {
            when (sort) {
                MovieSort.YEAR -> query.orderBy(movie.year.asc())
                MovieSort.TITLE -> query.orderBy(movie.title.asc())
            }
        }

        return query
    }
}