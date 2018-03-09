package edu.wpi.aiaa.repositories

import edu.wpi.aiaa.dao.StudentDao
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<StudentDao, Int>{

}