package com.stc.filemanager.controller;

import com.stc.filemanager.model.File;
import com.stc.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/create")
    public ResponseEntity<?> createFile(@RequestParam("folderName") String folderName,
                                        @RequestParam("fileName") String fileName,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("userEmail") String userEmail) {
        try {
            fileService.createFile(folderName, fileName, file.getBytes(), userEmail);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{fileId}/metadata")
    public ResponseEntity<File> getFileMetadata(@PathVariable Long fileId, @RequestParam String userEmail) {
        File fileMetadata = fileService.getFileMetadata(fileId, userEmail);
        return ResponseEntity.ok(fileMetadata);
    }
}

