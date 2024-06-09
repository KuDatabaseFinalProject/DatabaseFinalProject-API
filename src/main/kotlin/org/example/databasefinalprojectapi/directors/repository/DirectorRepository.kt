package org.example.databasefinalprojectapi.directors.repository

import org.example.databasefinalprojectapi.directors.entity.Director
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DirectorRepository : JpaRepository<Director, Long> {
    fun findAllByNameContains(name: String): List<Director>
}