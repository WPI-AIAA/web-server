package edu.wpi.aiaa.controllers

import edu.wpi.aiaa.models.ActivityRecordModel
import edu.wpi.aiaa.models.LocationRecordModel
import edu.wpi.aiaa.models.StudentPermissionRecordModel
import edu.wpi.aiaa.models.StudentRecordModel
import edu.wpi.aiaa.repositories.StudentRepository
import edu.wpi.aiaa.repositories.ActivityLogRepository
import edu.wpi.aiaa.repositories.LocationRepository
import edu.wpi.aiaa.repositories.StudentPermissionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@CrossOrigin
@RequestMapping("/stats")
class StatsController @Autowired constructor (
        val studentRepository: StudentRepository,
        val activityLogRepository: ActivityLogRepository,
        val locationRepository: LocationRepository,
        val studentPermissionRepository: StudentPermissionRepository
){
    // Essentially this all is just a way to provide user friendly info from the DB

    @GetMapping("/activity")
    @ResponseBody
    fun getSwipeLog(): Iterable<ActivityRecordModel>{

        // Create a list to return
        val activityList = ArrayList<ActivityRecordModel>()

        activityLogRepository.findAll().forEach {
            activityList.add(
                    ActivityRecordModel(
                        studentRepository.findById(it.studentId!!).get().userName!!,
                        locationRepository.findById(it.locationId!!).get().name!!,
                        it.action!!,
                        it.actionTime!!)
            )
        }
        return activityList //Return populated list
    }

    @GetMapping("/students")
    @ResponseBody
    fun getAllStudents(): Iterable<StudentRecordModel>{

        // Create a list to return
        val studentList = ArrayList<StudentRecordModel>()

        // For each StudentDao create a StudentRecordModel and add it to the list
        studentRepository.findAll().forEach {
            studentList.add(
                    StudentRecordModel(
                        it.firstName!!,
                        it.lastName!!,
                        it.userName!!,
                        it.studentId!!)
            )
        }
        return studentList //Return populated list
    }

    @GetMapping("/locations")
    @ResponseBody
    fun getAllLocations(): Iterable<LocationRecordModel>{

        // Create a list to return
        val locationList = ArrayList<LocationRecordModel>()

        locationRepository.findAll().forEach{
            locationList.add(
                    LocationRecordModel(
                            it.name!!,
                            it.state!!
                    )
            )
        }
        return locationList
    }

    @GetMapping("/permissions")
    @ResponseBody
    fun getAllStudentPermissions(): Iterable<StudentPermissionRecordModel>{

        // Create a list to return
        val permissionList = ArrayList<StudentPermissionRecordModel>()

        studentPermissionRepository.findAll().forEach{
            permissionList.add(
                    StudentPermissionRecordModel(
                            studentRepository.findById(it.studentId!!).get().userName!!,
                            locationRepository.findById(it.locationId!!).get().name!!,
                            it.permission!!
                    )
            )
        }
        return permissionList

    }

}
