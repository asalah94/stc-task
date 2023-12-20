package com.stc.filemanager.model;

import jakarta.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] binary;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
