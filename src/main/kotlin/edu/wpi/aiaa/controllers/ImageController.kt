package edu.wpi.aiaa.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.io.File

@Controller
@CrossOrigin
@RequestMapping("/images")
class ImageController @Autowired constructor(){

    // Root Source for Files
    val directory = "src/main/resources/files/images"

    @GetMapping("/home")
    @ResponseBody
    fun getImageNames(): Iterable<String>{

        // Source Directory for images
        // val directory = File("src/main/resources/files/images")

        // List of image names to return
        val imageList = ArrayList<String>()

        // If the folder doesn't exist then neither do the images
        if(!File(directory).isDirectory){
            return imageList
        }

        // Grab the names from each file
        File(directory).listFiles().forEach {

            // Extract the file extension
            val ext = it.name.substring(it.name.lastIndexOf('.') + 1).toLowerCase()

            // Only return files that are images
            if(ext == "png" || ext == "jpg" || ext == "jpeg" || ext == "gif"){
                imageList.add(it.name)
            }
        }

        return imageList
    }

    @GetMapping("/home/get")
    @ResponseBody
    fun getHomeImage(@RequestParam(required = true) fileName: String): ResponseEntity<ByteArray>{

        // This way a request cannot travel up the file tree /../../../
        if(fileName.contains("..")){
            return ResponseEntity.noContent().build()
        }

        val target = File("$directory/$fileName")

        if(!target.exists()){
            return ResponseEntity.noContent().build()
        }

        // Extract the file extension
        val ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase()

        // Determine the file type and send it
        if(ext == "png"){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG ).body(File("$directory/$fileName").readBytes())
        } else if(ext == "jpg" || ext == "jpeg"){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(File("$directory/$fileName").readBytes())
        } else if(ext == "gif"){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF ).body(File("$directory/$fileName").readBytes())
        }

        return ResponseEntity.noContent().build()
    }
}