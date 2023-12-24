package com.stc.filemanager.service;

import com.stc.filemanager.model.File;

import java.io.IOException;

public interface FileService {
    void createFile(String folderName, String fileName, byte[] binaryData, String userEmail);
    File getFileById(Long fileId, String userEmail) throws IOException;

}
