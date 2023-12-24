package com.stc.filemanager.service.impl;

import com.stc.filemanager.exception.BusinessCreationException;
import com.stc.filemanager.model.Item;
import com.stc.filemanager.model.ItemType;
import com.stc.filemanager.model.Permission;
import com.stc.filemanager.model.PermissionLevel;
import com.stc.filemanager.repository.ItemRepository;
import com.stc.filemanager.repository.PermissionRepository;
import com.stc.filemanager.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void createFolder(String spaceName, String folderName, String userEmail) {
        // Fetch the space by name
        Item space = itemRepository.findByName(spaceName);

        // Check if the user has EDIT access on this space
        Permission existingPermission = permissionRepository.findByUserEmailAndGroup(userEmail, space.getPermissionGroup());
        if (existingPermission == null || existingPermission.getPermissionLevel() != PermissionLevel.EDIT) {
            throw new BusinessCreationException("User does not have EDIT access on this space.");
        }

        // Create a new folder
        Item folder = new Item();
        folder.setName(folderName);
        folder.setType(ItemType.FOLDER);
        folder.setPermissionGroup(space.getPermissionGroup());
        itemRepository.save(folder);
    }

}
