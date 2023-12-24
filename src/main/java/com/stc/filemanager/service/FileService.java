package com.stc.filemanager.service;

import com.stc.filemanager.model.File;

public interface FileService {
    void createFile(String folderName, String fileName, byte[] binaryData, String userEmail);
    File getFileMetadata(Long fileId, String userEmail);
}
