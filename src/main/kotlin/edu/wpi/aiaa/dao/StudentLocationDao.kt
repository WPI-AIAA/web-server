package edu.wpi.aiaa.dao

import javax.persistence.*

@Entity
@Table(name="student_locations")
data class StudentLocationDao(
        @Id @GeneratedValue(strategy=GenerationType.AUTO)
        val id: Int? = null,
        val studentId: Int? = null,
        val locationId: Int? = null
)