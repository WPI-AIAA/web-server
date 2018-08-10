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
    fun getFileNames(): ResponseEntity<String> {

        // Idiot Check
        if(File(directory).isDirectory){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(iterateDirectory(File(directory)))
        } else {
            return ResponseEntity.noContent().build()
        }
    }

    // Sufficiently large tree structures might cause recursion issues
    fun iterateDirectory(source: File): String {

        val result = ArrayList<String>()

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

    @GetMapping("/usli/element")
    @ResponseBody
    fun getUSLIFile(@RequestParam(required = true) filePath: String): ResponseEntity<ByteArray>{

        val target = File("$directory/$filePath")

        if(!target.exists()){
            return ResponseEntity.noContent().build()
        }

        // Extract the file extension
        val ext = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase()

        // If the file is a pdf send it as one
        if(ext == "pdf"){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(target.readBytes())
        }

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + '"' + filePath.substring(filePath.lastIndexOf('/') + 1) + '"') // Sets file name that user sees when prompted for a download
                .contentType(MediaType.TEXT_PLAIN)
                .body(target.readBytes())
    }
}