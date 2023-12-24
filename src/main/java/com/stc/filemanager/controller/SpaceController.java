package com.stc.filemanager.controller;

import com.stc.filemanager.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("/create")
    public ResponseEntity<?> createSpace(@RequestBody Map<String, String> request) {
        spaceService.createSpace(request.get("name"));
        return ResponseEntity.ok().build();
    }
}

