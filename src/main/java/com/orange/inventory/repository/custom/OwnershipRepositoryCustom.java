package com.orange.inventory.repository.custom;

import java.util.HashMap;

public interface OwnershipRepositoryCustom {

	void acquireItem(String ownerName,Long itemId);
	
	void discardItem(Long userId,Long itemId);
	
	void discardAllItemsByUser(Long userId);
	
	void removeItemOwnership(Long itemId);
	
	HashMap<String, Object> findOwner(Long itemId);
	
}
