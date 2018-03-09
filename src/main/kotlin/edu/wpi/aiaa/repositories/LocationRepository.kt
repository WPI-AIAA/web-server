package edu.wpi.aiaa.repositories

import edu.wpi.aiaa.dao.LocationDao
import org.springframework.data.repository.CrudRepository

interface LocationRepository : CrudRepository<LocationDao, Int>{}