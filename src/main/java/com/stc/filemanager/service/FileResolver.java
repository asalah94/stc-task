package com.stc.filemanager.service;

import com.stc.filemanager.model.File;
import com.stc.filemanager.repository.FileRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileResolver implements GraphQLQueryResolver {

    private final FileRepository fileRepository;

    @Autowired
    public FileResolver(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File getFileMetadata(Long fileId, String userId) {
        return fileRepository.findFileByUserPermission(fileId, userId).orElse(null);
    }
}
