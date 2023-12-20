package com.stc.filemanager.model;

import jakarta.persistence.*;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup group;
}