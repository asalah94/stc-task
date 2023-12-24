package com.stc.filemanager.service.impl;

import com.stc.filemanager.exception.BusinessCreationException;
import com.stc.filemanager.model.*;
import com.stc.filemanager.repository.ItemRepository;
import com.stc.filemanager.repository.PermissionRepository;
import com.stc.filemanager.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderServiceImpl implements FolderService {

    private final ItemRepository itemRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public FolderServiceImpl(ItemRepository itemRepository, PermissionRepository permissionRepository) {
        this.itemRepository = itemRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void createFolder(String spaceName, String folderName, String userEmail) {
        Item space = fetchSpaceByName(spaceName);
        ensureUserHasEditPermission(userEmail, space);

        Item folder = createFolderItem(folderName, space.getPermissionGroup());
        itemRepository.save(folder);
    }

    private Item fetchSpaceByName(String spaceName) {
        return itemRepository.findByName(spaceName);
    }

    private void ensureUserHasEditPermission(String userEmail, Item space) {
        Permission existingPermission = permissionRepository.findByUserEmailAndGroup(userEmail, space.getPermissionGroup());
        if (existingPermission == null || existingPermission.getPermissionLevel() != PermissionLevel.EDIT) {
            throw new BusinessCreationException("User does not have EDIT access on this space.");
        }
    }

    private Item createFolderItem(String folderName, PermissionGroup permissionGroup) {
        Item folder = new Item();
        folder.setName(folderName);
        folder.setType(ItemType.FOLDER);
        folder.setPermissionGroup(permissionGroup);
        return folder;
    }
}
