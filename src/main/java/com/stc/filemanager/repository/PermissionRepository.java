package com.stc.filemanager.repository;

import com.stc.filemanager.model.Permission;
import com.stc.filemanager.model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByUserEmailAndGroup(String userEmail, PermissionGroup permissionGroup);
}
