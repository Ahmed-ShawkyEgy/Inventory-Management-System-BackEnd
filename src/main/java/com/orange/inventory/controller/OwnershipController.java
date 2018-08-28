package com.orange.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.inventory.payload.ApiResponse;
import com.orange.inventory.repository.custom.OwnershipRepositoryCustom;
import com.orange.inventory.wrapper.OwnershipWrapper;

@RestController
@RequestMapping("/api")
public class OwnershipController {
	
	@Autowired
	private OwnershipRepositoryCustom ownershipRepositoryCustom;
	
	@PostMapping("/acquire")
	public ResponseEntity<?> acquireItem(@RequestBody  OwnershipWrapper ownershipWrapper)
	{
		String ownerName = ownershipWrapper.getOwnerName();
		Long itemId = ownershipWrapper.getItemId();
		
		ownershipRepositoryCustom.acquireItem(ownerName, itemId);

		return ResponseEntity.ok().body(new ApiResponse(true, "Item acquired successfully"));
	}
	
	@PostMapping("/discard")
	public ResponseEntity<?> discardItem(@RequestBody  OwnershipWrapper ownershipWrapper)
	{
//		Long userId = ownershipWrapper.getUserId();
		Long itemId = ownershipWrapper.getItemId();
		
//		ownershipRepositoryCustom.discardItem(userId, itemId);
		ownershipRepositoryCustom.removeItemOwnership(itemId);
		
		return ResponseEntity.ok().body(new ApiResponse(true, "Item discarded successfully"));
	}
	
	@GetMapping("/owner/{id}")
	public ResponseEntity<?> findOwner(@PathVariable(value = "id") Long itemId) {
		return ResponseEntity.ok().body(ownershipRepositoryCustom.findOwner(itemId));
    }

}
