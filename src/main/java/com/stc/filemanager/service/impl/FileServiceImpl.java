package com.stc.filemanager.service.impl;

import com.stc.filemanager.exception.BusinessCreationException;
import com.stc.filemanager.model.*;
import com.stc.filemanager.repository.FileRepository;
import com.stc.filemanager.repository.ItemRepository;
import com.stc.filemanager.repository.PermissionRepository;
import com.stc.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void createFile(String folderName, String fileName, byte[] binaryData, String userEmail) {
        Item folder = itemRepository.findByName(folderName);

        // Check if the user has EDIT access on this folder
        Permission existingPermission = permissionRepository.findByUserEmailAndGroup(userEmail, folder.getPermissionGroup());
        if (existingPermission == null || existingPermission.getPermissionLevel() != PermissionLevel.EDIT) {
            throw new BusinessCreationException("User does not have EDIT access on this file.");
        }

        // Create a new file item
        Item fileItem = new Item();
        fileItem.setName(fileName);
        fileItem.setType(ItemType.FILE);
        fileItem.setPermissionGroup(folder.getPermissionGroup());
        itemRepository.save(fileItem);

        // Save the binary data as content for the file
        File fileEntity = new File();
        fileEntity.setContent(binaryData);
        fileEntity.setItem(fileItem);
        fileRepository.save(fileEntity);
    }

    @Override
    public File getFileMetadata(Long fileId, String userEmail) {
        // Implement the logic to check user access and fetch file metadata
        // This is a basic representation. You'll need to integrate it with your data access layer.
        return fileRepository.findById(fileId).orElse(null);
    }
}
