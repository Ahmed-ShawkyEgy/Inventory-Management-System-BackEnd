package com.orange.inventory.repository.custom;

import java.util.HashMap;
import java.util.List;

public interface ItemRepositoryCustom {

	public List<HashMap<String, Object>> findAllItems();
	
	public HashMap<String, Object> findItem(Long itemId);
}
