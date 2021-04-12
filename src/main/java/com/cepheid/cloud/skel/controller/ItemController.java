package com.cepheid.cloud.skel.controller;

import com.cepheid.cloud.skel.exceptions.CreateException;
import com.cepheid.cloud.skel.exceptions.UpdateException;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.service.item.ItemService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


// curl http:/localhost:9443/app/api/1.0/items

@RestController
@Path("/api/1.0/items")
@Api()
public class ItemController {

    private final ItemService itemService;
    private final String ENTITY_NAME = "Item";

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Item>> getItems(Pageable pageable) {
        List<Item> result = itemService.getAllItem(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/1.0/items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item result = itemService.getItem(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Item> saveItem(@RequestBody Item item) throws CreateException {
        if (item != null && item.getId() != null) {
            throw new CreateException(ENTITY_NAME);
        }
        Item result = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Item> updateItem(@RequestBody Item item) throws UpdateException {
        if (item != null && item.getId() == null) {
            throw new UpdateException(ENTITY_NAME);
        }
        Item result = itemService.updateItem(item);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @DeleteMapping("/api/1.0/items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
