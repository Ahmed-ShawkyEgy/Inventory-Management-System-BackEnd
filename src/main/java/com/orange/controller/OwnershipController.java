package com.orange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.repository.custom.OwnershipRepositoryCustom;
import com.orange.wrapper.OwnershipWrapper;

@RestController
@RequestMapping("/api")
public class OwnershipController {
	
	@Autowired
	private OwnershipRepositoryCustom ownershipRepositoryCustom;
	
	@PostMapping("/acquire")
	public ResponseEntity<?> acquireItem(@RequestBody  OwnershipWrapper ownershipWrapper)
	{
		Long userId = ownershipWrapper.getUserId();
		Long itemId = ownershipWrapper.getItemId();
		
		ownershipRepositoryCustom.acquireItem(userId, itemId);

		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/discard")
	public ResponseEntity<String> discardItem(@RequestBody  OwnershipWrapper ownershipWrapper)
	{
		Long userId = ownershipWrapper.getUserId();
		Long itemId = ownershipWrapper.getItemId();
		
		ownershipRepositoryCustom.discardItem(userId, itemId);
		
		return ResponseEntity.ok().build();
	}

}
