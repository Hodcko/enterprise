package com.github.hodcko.multy.web.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping
public class FileManager {

    private String rootPath = "/Users/hdcko/Программы/apache-tomcat-8.5.53/temp/files/";


    @PostMapping("/upload")
    public String processFile(@RequestParam(value = "avatar") MultipartFile image, HttpSession session) {
        String fileName = (String) session.getAttribute("email");
        processImage(image, fileName);
        return "SuccessRegistrationNewUser";
    }

    @RequestMapping(value = "/download/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam(value = "name") String fileName) {
        File file;
        file = new File(rootPath + fileName);
        if (file.exists()) {
            byte[] content = new byte[0];
            try {
                content = FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                //Exception handling
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(content.length);
            return new ResponseEntity<>(content, headers, HttpStatus.OK);

        } else {
            file = new File(rootPath + "default.jpg");
            byte[] content = new byte[0];
            try {
                content = FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                //Exception handling
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(content.length);
            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        }
    }

    private void processImage(MultipartFile image, String fileName) {
        try {
            if (image != null && !image.isEmpty()) {
                validateImage(image);
                saveImage(fileName, image);
            }
        } catch (IOException e) {
            //Error handling
        }
    }

    private void validateImage(MultipartFile image) throws IOException {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new IOException("Only JPG images accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws IOException {
        File file = new File(rootPath + filename + ".jpg");
        FileUtils.writeByteArrayToFile(file, image.getBytes());
    }
}
