package com.orange.inventory.repository.custom;

import java.util.HashMap;
import java.util.List;

public interface OwnershipRepositoryCustom {

	void acquireItem(Long userId,Long itemId);
	
	void discardItem(Long userId,Long itemId);
	
	void discardAllItemsByUser(Long userId);
	
	void removeItemOwnership(Long itemId);
	
	List<HashMap<String, Object>> findOwner(Long itemId);
	
}
