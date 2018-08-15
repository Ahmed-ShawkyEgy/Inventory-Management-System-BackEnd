package com.orange.inventory.repository.custom.impl;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.orange.inventory.helper.QueryHelper;
import com.orange.inventory.repository.custom.ItemRepositoryCustom;

@Repository
@Transactional
@Component
public class ItemRepositoryImpl implements ItemRepositoryCustom{


    @PersistenceContext
    EntityManager entityManager;
	
	@Override
	public List<HashMap<String, Object>> findAllItems() {
		
		Query query = entityManager.createNativeQuery(
				"SELECT i.id , i.name , i.description , i.price , i.purchase_date,"
				+ "u.id as 'user_id',u.email,u.first_name, u.last_name "
				+ "FROM items i LEFT JOIN users u "
				+ "ON i.owner = u.id");
		
		String[] columns = "item_id,item_name,item_description,item_price,purchase_date,owner_id,owner_email,owner_first_name,owner_last_name".split(",");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.getResultList();
		return QueryHelper.queryToMapList(rows, columns);		
	}

	@Override
	public HashMap<String, Object> findItem(Long itemId) {
		Query query = entityManager.createNativeQuery(
				"SELECT i.id , i.name , i.description , i.price , i.purchase_date,"
				+ "u.id as 'user_id',u.email,u.first_name, u.last_name "
				+ "FROM items i LEFT JOIN users u "
				+ "ON i.owner = u.id "
				+ "WHERE i.id=?");
		query.setParameter(1, itemId);
		String[] columns = "item_id,item_name,item_description,item_price,purchase_date,owner_id,owner_email,owner_first_name,owner_last_name".split(",");
		Object[] row = (Object[]) query.getSingleResult();
		return QueryHelper.queryToMap(row, columns);		
	}

	
}
