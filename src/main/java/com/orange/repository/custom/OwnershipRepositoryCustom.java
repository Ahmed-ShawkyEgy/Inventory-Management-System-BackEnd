package com.orange.repository.custom;

public interface OwnershipRepositoryCustom {

	void acquireItem(Long userId,Long itemId);
	
	void discardItem(Long userId,Long itemId);
	
}
