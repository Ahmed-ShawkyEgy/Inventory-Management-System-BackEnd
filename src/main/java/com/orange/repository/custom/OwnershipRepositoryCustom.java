package com.orange.repository.custom;

import java.util.List;

import com.orange.model.User;

public interface OwnershipRepositoryCustom {

	void acquireItem(Long userId,Long itemId);
	
	void discardItem(Long userId,Long itemId);
	
	void discardAllItemsByUser(Long userId);
	
	void discardAllItemsByItem(Long itemId);
	
	List<User> findOwner(Long itemId);
	
}
