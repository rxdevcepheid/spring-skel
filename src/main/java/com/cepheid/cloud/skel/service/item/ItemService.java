package com.cepheid.cloud.skel.service.item;

import com.cepheid.cloud.skel.model.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    List<Item> getAllItem(Pageable pageable);

    Item getItem(Long id);

    Item createItem(Item item);

    Item updateItem(Item item);

    void deleteItem(Long id);
}
