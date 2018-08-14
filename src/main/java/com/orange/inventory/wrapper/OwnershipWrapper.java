package com.orange.inventory.wrapper;

public class OwnershipWrapper {

	private Long userId;
	private Long itemId;
	
	public OwnershipWrapper(Long userId,Long itemId) {

		this.userId = userId;
		this.itemId = itemId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	
	
}
