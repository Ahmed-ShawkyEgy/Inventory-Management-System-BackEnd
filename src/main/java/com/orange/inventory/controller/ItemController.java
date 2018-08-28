package com.orange.inventory.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.inventory.exception.ResourceNotFoundException;
import com.orange.inventory.model.Item;
import com.orange.inventory.repository.ItemRepository;
import com.orange.inventory.repository.custom.OwnershipRepositoryCustom;


@RestController
@RequestMapping("/api")
public class ItemController {
	
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OwnershipRepositoryCustom ownershipRepositoryCustom;
    
    @GetMapping("/items")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllItems()
    {
//    	return itemRepository.findAll();
//    	return ResponseEntity.ok().body(itemRepository.findAllItems(offset,limit));
    	return ResponseEntity.ok().body(itemRepository.findAllItems());
    }
    
    // Create
    @PostMapping("/items")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> registerItem(@Valid @RequestBody Item item)
    {
    	Item myItem = itemRepository.save(item);
    	System.out.println(myItem+" Created successfully");
    	return ResponseEntity.ok().body(myItem);
    }
    
    // Read
    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItemById(@PathVariable(value = "id") Long itemId) {
		return ResponseEntity.ok().body(itemRepository.findItem(itemId));
    }
    
    // Update
    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable(value = "id") Long itemId,
            @Valid @RequestBody Item itemDetails) {

		Item item = itemRepository.findById(itemId)
		.orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

		item.setName(itemDetails.getName());
		item.setPrice(itemDetails.getPrice());
		item.setDescription(itemDetails.getDescription());
		item.setPurchase_date(itemDetails.getPurchase_date());
		
		Item updatedItem = itemRepository.save(item);
		System.out.println(updatedItem + " updated successfully");
		return ResponseEntity.ok().body(updatedItem);
	}
    
    // Delete
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

        ownershipRepositoryCustom.removeItemOwnership(itemId);
        itemRepository.delete(item);

        return ResponseEntity.ok().build();
    }
    
}
