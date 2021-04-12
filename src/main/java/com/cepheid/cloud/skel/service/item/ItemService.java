package com.cepheid.cloud.skel.service.item;

import com.cepheid.cloud.skel.model.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    /**
     * Get all Items
     * @param pageable
     * @return
     */
    List<Item> getAllItem(Pageable pageable);

    /**
     * Get Item based on Id
     * @param id
     * @return
     */
    Item getItem(Long id);

    /**
     * Creates New Item
     * @param item
     * @return
     */
    Item createItem(Item item);

    /**
     * Updates an existing Item
     * @param item
     * @return
     */
    Item updateItem(Item item);

    /**
     * Deletes an exesting Item
     * @param id
     */
    void deleteItem(Long id);
}
