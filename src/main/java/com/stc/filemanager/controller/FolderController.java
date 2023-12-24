package com.stc.filemanager.controller;

import com.stc.filemanager.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @PostMapping("/create")
    public ResponseEntity<?> createFolder(@RequestBody Map<String, String> request) {
        folderService.createFolder(request.get("spaceName"), request.get("folderName"), request.get("userEmail"));
        return ResponseEntity.ok().build();
    }
}

