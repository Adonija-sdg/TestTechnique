package com.teste.statistiques.clients.controller.webControllers;

import com.teste.statistiques.clients.service.FileUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/error";
        }
        try {
            FileUploadService.saveFile(file);
        } catch (IOException e) {
            return "redirect:/error";
        }
        return "redirect:/index";
    }
}
