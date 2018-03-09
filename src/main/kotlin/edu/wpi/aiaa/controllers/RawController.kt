package edu.wpi.aiaa.controllers

import edu.wpi.aiaa.dao.*
import edu.wpi.aiaa.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/vomit")
class RawController @Autowired constructor(
        val studentRepository: StudentRepository,
        val activityLogRepository: ActivityLogRepository,
        val locationRepository: LocationRepository,
        val studentLocationRepository: StudentLocationRepository,
        val studentPermissionRepository: StudentPermissionRepository
){
    // This whole controller only really serves to vomit up info DIRECTLY from the DB
    @GetMapping("/students")
    @ResponseBody
    fun getStudentsTable(): Iterable<StudentDao>{
        return studentRepository.findAll()
    }

    @GetMapping("/locations")
    @ResponseBody
    fun getLocationsTable(): Iterable<LocationDao>{
        return locationRepository.findAll()
    }

    @GetMapping("/student_locations")
    @ResponseBody
    fun getStudentLocationsTable(): Iterable<StudentLocationDao>{
        return studentLocationRepository.findAll()
    }

    @GetMapping("/student_permissions")
    @ResponseBody
    fun getStudentPermissionsTable(): Iterable<StudentPermissionDao>{
        return studentPermissionRepository.findAll()
    }

    @GetMapping("/activity_logs")
    @ResponseBody
    fun getActivityLogsTable(): Iterable<ActivityLogDao>{
        return activityLogRepository.findAll()
    }
}