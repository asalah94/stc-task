package com.stc.filemanager.service.impl;

import com.stc.filemanager.model.*;
import com.stc.filemanager.repository.ItemRepository;
import com.stc.filemanager.repository.PermissionGroupRepository;
import com.stc.filemanager.repository.PermissionRepository;
import com.stc.filemanager.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final PermissionGroupRepository permissionGroupRepository;
    private final PermissionRepository permissionRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public SpaceServiceImpl(PermissionGroupRepository permissionGroupRepository,
                            PermissionRepository permissionRepository,
                            ItemRepository itemRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
        this.permissionRepository = permissionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void createSpace(String spaceName) {
        PermissionGroup group = createPermissionGroup();
        Permission viewPermission = createPermissionForUser("admin@example.com", PermissionLevel.VIEW, group);
        Permission editPermission = createPermissionForUser("admin2@example.com", PermissionLevel.EDIT, group);

        Item space = createSpaceItem(spaceName, group);

        permissionGroupRepository.save(group);
        permissionRepository.saveAll(List.of(viewPermission, editPermission));
        itemRepository.save(space);
    }

    private PermissionGroup createPermissionGroup() {
        PermissionGroup group = new PermissionGroup();
        group.setGroupName("admin_group");
        return group;
    }

    private Permission createPermissionForUser(String userEmail, PermissionLevel permissionLevel, PermissionGroup group) {
        Permission permission = new Permission();
        permission.setUserEmail(userEmail);
        permission.setPermissionLevel(permissionLevel);
        permission.setGroup(group);
        return permission;
    }

    private Item createSpaceItem(String spaceName, PermissionGroup group) {
        Item space = new Item();
        space.setName(spaceName);
        space.setType(ItemType.SPACE);
        space.setPermissionGroup(group);
        return space;
    }
}
