package com.orange.inventory.repository.custom;

public interface OwnershipRepositoryCustom {

	void acquireItem(String ownerName,Long itemId);
	
	void removeItemOwnership(Long itemId);
	
}
