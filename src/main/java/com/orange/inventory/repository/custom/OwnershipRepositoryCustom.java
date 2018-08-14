package com.orange.inventory.repository.custom;

import java.util.List;

import com.orange.inventory.model.User;

public interface OwnershipRepositoryCustom {

	void acquireItem(Long userId,Long itemId);
	
	void discardItem(Long userId,Long itemId);
	
	void discardAllItemsByUser(Long userId);
	
	void removeItemOwnership(Long itemId);
	
	List<User> findOwner(Long itemId);
	
}
