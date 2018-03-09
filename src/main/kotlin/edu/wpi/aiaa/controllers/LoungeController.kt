package edu.wpi.aiaa.controllers

import edu.wpi.aiaa.models.StudentRecordModel
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@RequestMapping("/login/lounge")
class LoungeController @Autowired constructor(){

    @GetMapping("/students")
    @ResponseBody
    fun getAllStudents(): Iterable<StudentRecordModel>{
        TODO()
    }


    @GetMapping("/status")
    @ResponseBody
    fun getStatus(): String{
        return "open"
    }
}