package edu.wpi.aiaa.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.io.File

@Controller
@CrossOrigin
@RequestMapping("/files")
class FileController @Autowired constructor(){

    // Root Source for Files
    val directory = "src/main/resources/files/usli"

    data class FileTreeElement(val name: String, val path: String, val type: String, val children: ArrayList<FileTreeElement> = ArrayList())

    @GetMapping("/usli")
    @ResponseBody
    fun getFileNames(): ResponseEntity<FileTreeElement> {

        val rootFile = File(directory)

        // Idiot Check
        if(!rootFile.exists()){
            // Not a valid file
            return ResponseEntity.noContent().build()
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(iterateFileTree(rootFile))
        }
    }

    fun iterateFileTree(rootFile: File): FileTreeElement {

        if(!rootFile.isDirectory){

            // Extract the file extension
            val ext = rootFile.name.substring(rootFile.name.lastIndexOf('.') + 1).toLowerCase()

            return FileTreeElement(rootFile.name, rootFile.path.substring(directory.length),"file")
        } else {
            val children = ArrayList<FileTreeElement>()

            rootFile.listFiles().forEach {
                children.add(iterateFileTree(it))
            }

            return FileTreeElement(rootFile.name, rootFile.path.substring(directory.length), "directory", children)
        }

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