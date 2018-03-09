package edu.wpi.aiaa.repositories

import edu.wpi.aiaa.dao.StudentLocationDao
import org.springframework.data.repository.CrudRepository


interface StudentLocationRepository : CrudRepository<StudentLocationDao, Int>