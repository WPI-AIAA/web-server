package edu.wpi.aiaa.dao

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name="activity_logs")
data class ActivityLogDao(
        @Id @GeneratedValue(strategy= GenerationType.AUTO)
        val id: Int? = null,
        val studentId: Int? = null,
        val locationId: Int? = null,
        val action: String? = null,
        val actionTime: Timestamp? = null
)