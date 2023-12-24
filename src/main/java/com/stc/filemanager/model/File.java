package com.stc.filemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] content;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(mappedBy = "file")
    private Permission permission;

}
