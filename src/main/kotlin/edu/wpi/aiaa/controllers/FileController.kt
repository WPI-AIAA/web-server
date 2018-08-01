package edu.wpi.aiaa.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.io.File

@Controller
@RequestMapping("/files")
class FileController @Autowired constructor(){

    // Root Source for Files
    val directory = "src/main/resources/files/usli"

    @GetMapping("/usli")
    @ResponseBody
    fun getFileNames(): String {

        // Idiot Check
        if(File(directory).isDirectory){
            return iterateDirectory(File(directory))
        } else {
            return ""
        }
    }

    // Sufficiently large tree structures might cause recursion issues
    fun iterateDirectory(source: File): String {

        val result = ArrayList<String>()

        // TODO: JSON Formatting

        source.listFiles().forEach {
            if (it.isDirectory) {
                result.add('"' + it.name + '"')
                result.add(iterateDirectory(it))
            } else if (it.isFile) {
                result.add('"' + it.name + '"')
            }
        }

        return result.toString()
    }

//    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("[\"main-example.pdf\", \"sub-directory-1\", [\"sub-example-1.pdf\", \"sub-example-2.pdf\"]]");

    @GetMapping("/usli/element")
    @ResponseBody
    fun getUSLIFile(@RequestParam(required = true) filePath: String): ResponseEntity<ByteArray>{

        val target = File(directory + '/' + filePath)

        if(!target.exists()){
            return ResponseEntity.noContent().build()
        }

        // Extract the file extension
        val ext = filePath.substring(filePath.lastIndexOf('.') + 1)

        // If the file is a pdf send it as one
        if(ext == "pdf" || ext == "PDF"){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(target.readBytes())
        }

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + '"' + filePath.substring(filePath.lastIndexOf('/') + 1) + '"')
                .contentType(MediaType.TEXT_PLAIN)
                .body(target.readBytes())
    }
}