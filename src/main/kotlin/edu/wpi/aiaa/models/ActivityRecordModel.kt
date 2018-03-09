package edu.wpi.aiaa.models

import java.sql.Timestamp

data class ActivityRecordModel(
        val userName: String,
        val location: String,
        val action: String,
        val actionTime: Timestamp){}


