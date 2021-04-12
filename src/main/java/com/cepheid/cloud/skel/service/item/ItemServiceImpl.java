package com.cepheid.cloud.skel.service.item;

import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.ItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public List<Item> getAllItem(Pageable pageable) {
        return itemRepository.findAll(pageable).getContent();
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public Item createItem(Item item){
        Item result = itemRepository.save(item);
        return result;
    }

    @Override
    public Item updateItem(Item item) {
        Item result = itemRepository.save(item);
        return result;
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
