package edu.wpi.aiaa.dao

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name="students")
data class StudentDao(
        @Id @GeneratedValue(strategy=GenerationType.AUTO)
        val id: Int? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val userName: String? = null,
        val studentId: Int? = null,
        @GeneratedValue(strategy=GenerationType.AUTO)
        val registered: Timestamp? = null
)