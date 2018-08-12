package com.orange.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.exception.ResourceNotFoundException;
import com.orange.model.Item;
import com.orange.repository.ItemRepository;
import com.orange.repository.custom.OwnershipRepositoryCustom;


@RestController
@RequestMapping("/api")
public class ItemController {
	
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OwnershipRepositoryCustom ownershipRepositoryCustom;
    
    @GetMapping("/items")
    public List<Item> getAllItems()
    {
    	return itemRepository.findAll();
    }
    
    // Create
    @PostMapping("/items")
    public Item registerItem(@Valid @RequestBody Item item)
    {
    	return itemRepository.save(item);
    }
    
    // Read
    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable(value = "id") Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));
    }
    
    // Update
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable(value = "id") Long itemId,
            @Valid @RequestBody Item itemDetails) {

		Item item = itemRepository.findById(itemId)
		.orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

		item.setName(itemDetails.getName());
		item.setDescription(itemDetails.getDescription());
		item.setPurchase_date(itemDetails.getPurchase_date());
		
		Item updatedItem = itemRepository.save(item);
		return updatedItem;
	}
    
    // Delete
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

        ownershipRepositoryCustom.discardAllItemsByItem(itemId);
        itemRepository.delete(item);

        return ResponseEntity.ok().build();
    }
    
    

}
