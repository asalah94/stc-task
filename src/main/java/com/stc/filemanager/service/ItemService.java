package com.stc.filemanager.service;

import com.stc.filemanager.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllItems();

    Optional<Item> getItemById(Long id);

    Item createItem(Item item);

}
