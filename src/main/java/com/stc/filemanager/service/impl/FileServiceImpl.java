package com.stc.filemanager.service.impl;

import com.stc.filemanager.exception.BusinessCreationException;
import com.stc.filemanager.model.*;
import com.stc.filemanager.repository.FileRepository;
import com.stc.filemanager.repository.ItemRepository;
import com.stc.filemanager.repository.PermissionRepository;
import com.stc.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final ItemRepository itemRepository;
    private final FileRepository fileRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public FileServiceImpl(ItemRepository itemRepository,
                           FileRepository fileRepository,
                           PermissionRepository permissionRepository) {
        this.itemRepository = itemRepository;
        this.fileRepository = fileRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void createFile(String folderName, String fileName, byte[] binaryData, String userEmail) {
        Item folder = fetchFolderByName(folderName);
        ensureUserHasEditPermission(userEmail, folder);

        Item fileItem = createFileItem(fileName, folder.getPermissionGroup());
        File fileEntity = createFileEntity(binaryData, fileItem);

        fileRepository.save(fileEntity);
    }

    private Item fetchFolderByName(String folderName) {
        return itemRepository.findByName(folderName);
    }

    private void ensureUserHasEditPermission(String userEmail, Item folder) {
        Permission existingPermission = permissionRepository.findByUserEmailAndGroup(userEmail, folder.getPermissionGroup());
        if (existingPermission == null || existingPermission.getPermissionLevel() != PermissionLevel.EDIT) {
            throw new BusinessCreationException("User does not have EDIT access on this file.");
        }
    }

    private Item createFileItem(String fileName, PermissionGroup permissionGroup) {
        Item fileItem = new Item();
        fileItem.setName(fileName);
        fileItem.setType(ItemType.FILE);
        fileItem.setPermissionGroup(permissionGroup);
        return itemRepository.save(fileItem);
    }

    private File createFileEntity(byte[] binaryData, Item fileItem) {
        File fileEntity = new File();
        fileEntity.setContent(binaryData);
        fileEntity.setItem(fileItem);
        return fileEntity;
    }

    @Override
    public File getFileById(Long fileId, String userEmail) throws IOException {
        Optional<File> fileOptional = fileRepository.findById(fileId);
        if (fileOptional.isEmpty()) {
            throw new BusinessCreationException("File not found");
        }

        Permission permission = permissionRepository.findByUserEmailAndFileId(userEmail, fileId);
        if (permission == null) {
            throw new BusinessCreationException("User does not have access to this file");
        }

        return fileOptional.get();
    }
}
