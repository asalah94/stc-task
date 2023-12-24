package com.stc.filemanager.repository;

import com.stc.filemanager.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findFileByUserPermission(Long fileId, String userId);
}

