package edu.wpi.aiaa.repositories

import edu.wpi.aiaa.dao.ActivityLogDao
import org.springframework.data.repository.CrudRepository

interface ActivityLogRepository : CrudRepository<ActivityLogDao, Int>{}