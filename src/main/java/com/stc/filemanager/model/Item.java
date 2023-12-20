package com.stc.filemanager.model;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;
}
