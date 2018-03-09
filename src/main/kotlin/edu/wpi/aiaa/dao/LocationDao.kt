package edu.wpi.aiaa.dao

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name="locations")
data class LocationDao(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        val name: String? = null,
        val state: String? = null,
        val created: Timestamp? = null
)