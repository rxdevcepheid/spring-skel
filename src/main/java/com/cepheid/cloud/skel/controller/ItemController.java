package com.cepheid.cloud.skel.controller;

import com.cepheid.cloud.skel.exceptions.CreateException;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import com.cepheid.cloud.skel.repository.ItemRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Optional;


// curl http:/localhost:9443/app/api/1.0/items

@RestController
@Path("/api/1.0/items")
@Api()
public class ItemController {

    private final ItemRepository mItemRepository;
    private final DescriptionRepository mDescriptionRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository, DescriptionRepository mDescriptionRepository) {
        mItemRepository = itemRepository;
        this.mDescriptionRepository = mDescriptionRepository;
    }

    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ResponseEntity<Collection<Item>> getItems() {
        Collection result = mItemRepository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/1.0/items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> result = mItemRepository.findById(id);
        return ResponseEntity.ok(result.get());
    }

    @PostMapping
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Item> saveItem(@RequestBody Item item) throws CreateException {
        if (item != null && item.getId() != null) {
            throw new CreateException("Could not create new item as Id is present");
        }
        Item result = mItemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Item> updateItem(@RequestBody Item item) throws CreateException {
        if (item != null && item.getId() == null) {
            throw new CreateException("Could not update item as Id is not present");
        }

        Item result = mItemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @DeleteMapping("/api/1.0/items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteItem(@PathVariable Long id) {
        mItemRepository.deleteById(id);
    }
}
