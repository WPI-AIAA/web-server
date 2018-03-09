package edu.wpi.aiaa.repositories

import edu.wpi.aiaa.dao.StudentPermissionDao
import org.springframework.data.repository.CrudRepository

interface StudentPermissionRepository : CrudRepository<StudentPermissionDao, Int>{}