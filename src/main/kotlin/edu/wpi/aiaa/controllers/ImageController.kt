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


    @GetMapping("/home")
    @ResponseBody
    fun getImageNames(): Iterable<String>{

        // Source Directory for images
        val directory = File("src/main/resources/files/images")

        // List of image names to return
        val imageList = ArrayList<String>()

        // If the folder doesn't exist then neither do the images
        if(!directory.isDirectory){
            return imageList
        }

        // Grab the names from each file
        directory.listFiles().forEach {

            // Extract the file extension
            val ext = it.name.substring(it.name.lastIndexOf('.') + 1)

            // Only return files that are images
            if(ext == "png" || ext == "PNG" || ext == "jpg" || ext == "JPG" || ext == "jpeg" || ext == "JPEG" || ext == "gif" || ext == "GIF"){
                imageList.add(it.name)
            }
        }

        return imageList
    }

    @GetMapping("/home/{fileName}")
    @ResponseBody
    fun getHomeImage(@PathVariable fileName: String): ResponseEntity<ByteArray>{

        // General Source Directory
        val directory = "src/main/resources/files/images"

        // Check the file exists
        if(!File(directory + '/' + fileName).exists()){
            return ResponseEntity.noContent().build()
        }

        // Grab the file extension
        val extension = fileName.substring(fileName.lastIndexOf('.') + 1)

        // Determine the image file type and send it
        if(extension == "png" || extension == "PNG"){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(File(directory + '/' + fileName).readBytes())
        } else if(extension == "jpg" || extension == "JPG" || extension == "jpeg" || extension == "JPEG"){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(File(directory + '/' + fileName).readBytes())
        } else if(extension == "gif" || extension == "GIF"){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(File(directory + '/' + fileName).readBytes())
        }

        // TODO: Send proper error response
        return ResponseEntity.noContent().build()
    }

}