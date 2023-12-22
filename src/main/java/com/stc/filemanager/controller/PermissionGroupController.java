package com.stc.filemanager.controller;

import com.stc.filemanager.model.PermissionGroup;
import com.stc.filemanager.service.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission-groups")
public class PermissionGroupController {

    @Autowired
    private PermissionGroupService permissionGroupService;

    @GetMapping
    public ResponseEntity<List<PermissionGroup>> getAllPermissionGroups() {
        return ResponseEntity.ok(permissionGroupService.getAllPermissionGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionGroup> getPermissionGroupById(@PathVariable Long id) {
        return permissionGroupService.getPermissionGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PermissionGroup> createPermissionGroup(@RequestBody PermissionGroup permissionGroup) {
        return ResponseEntity.ok(permissionGroupService.createPermissionGroup(permissionGroup));
    }
}
