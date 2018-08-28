package com.orange.inventory.wrapper;

public class OwnershipWrapper {

	private String ownerName;
	private Long itemId;
	
	public OwnershipWrapper(String ownerName,Long itemId) {

		this.ownerName = ownerName;
		this.itemId = itemId;
	}
	

	
	public String getOwnerName() {
		return ownerName;
	}



	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}



	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	
	
}
