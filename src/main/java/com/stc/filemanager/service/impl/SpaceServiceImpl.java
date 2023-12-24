package com.stc.filemanager.service.impl;

import com.stc.filemanager.model.*;
import com.stc.filemanager.repository.ItemRepository;
import com.stc.filemanager.repository.PermissionGroupRepository;
import com.stc.filemanager.repository.PermissionRepository;
import com.stc.filemanager.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void createSpace(String spaceName) {
        PermissionGroup group = new PermissionGroup();
        group.setGroupName("admin_group");
        permissionGroupRepository.save(group);

        Permission viewPermission = new Permission();
        viewPermission.setUserEmail("admin@example.com");
        viewPermission.setPermissionLevel(PermissionLevel.VIEW);
        viewPermission.setGroup(group);
        permissionRepository.save(viewPermission);

        Permission editPermission = new Permission();
        editPermission.setUserEmail("admin2@example.com");
        editPermission.setPermissionLevel(PermissionLevel.EDIT);
        editPermission.setGroup(group);
        permissionRepository.save(editPermission);

        Item space = new Item();
        space.setName(spaceName);
        space.setType(ItemType.SPACE);
        space.setPermissionGroup(group);
        itemRepository.save(space);
    }
}

