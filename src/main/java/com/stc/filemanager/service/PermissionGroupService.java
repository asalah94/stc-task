package com.stc.filemanager.service;

import com.stc.filemanager.model.PermissionGroup;

import java.util.List;
import java.util.Optional;

public interface PermissionGroupService {

    List<PermissionGroup> getAllPermissionGroups();

    Optional<PermissionGroup> getPermissionGroupById(Long id);

    PermissionGroup createPermissionGroup(PermissionGroup permissionGroup);

}
