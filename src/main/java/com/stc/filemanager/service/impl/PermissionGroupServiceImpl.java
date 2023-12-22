package com.stc.filemanager.service.impl;

import com.stc.filemanager.model.PermissionGroup;
import com.stc.filemanager.repository.PermissionGroupRepository;
import com.stc.filemanager.service.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Override
    public List<PermissionGroup> getAllPermissionGroups() {
        return permissionGroupRepository.findAll();
    }

    @Override
    public Optional<PermissionGroup> getPermissionGroupById(Long id) {
        return permissionGroupRepository.findById(id);
    }

    @Override
    public PermissionGroup createPermissionGroup(PermissionGroup permissionGroup) {
        return permissionGroupRepository.save(permissionGroup);
    }

}
