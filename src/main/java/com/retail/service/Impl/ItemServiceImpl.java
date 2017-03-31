package com.retail.service.Impl;


import com.retail.model.Item;
import com.retail.repository.ItemRepository;
import com.retail.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private ItemRepository itemRepository;

    public void setItemRepository(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item save(Item item) {
        return this.itemRepository.save(item);
    }

    @Transactional
    public Item findById(Integer id) {
        return this.itemRepository.findById(id);
    }

    @Transactional
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Transactional
    public Item update(Item item) {
        return this.itemRepository.update(item);
    }

    @Transactional
    public Item  delete(Integer id) {
        return this.itemRepository.delete(id);
    }
}