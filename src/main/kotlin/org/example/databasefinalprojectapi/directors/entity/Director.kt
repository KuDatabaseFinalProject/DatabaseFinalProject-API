package org.example.databasefinalprojectapi.directors.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "director")
class Director(
    @Id
    val id: Long,
    val name: String,
)