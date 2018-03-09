package edu.wpi.aiaa.controllers

import edu.wpi.aiaa.dao.StudentDao
import edu.wpi.aiaa.models.StudentRecordModel
import edu.wpi.aiaa.repositories.StudentRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/example")
class ExampleController @Autowired constructor(val studentRepository: StudentRepository){

    @PostMapping("/add")
    @ResponseBody
    fun addNewStudent(
            @RequestParam firstName: String,
            @RequestParam lastName: String,
            @RequestParam userName: String,
            @RequestParam studentId: Int
    ): String{
        val student = StudentDao(0, firstName, lastName, userName, studentId)
        studentRepository.save(student)
        return "Saved!"
    }

}
