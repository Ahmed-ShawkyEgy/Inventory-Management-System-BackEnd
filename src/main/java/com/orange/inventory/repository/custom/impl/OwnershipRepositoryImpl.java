package com.orange.inventory.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.orange.inventory.repository.custom.OwnershipRepositoryCustom;

@Repository
@Transactional
@Component
public class OwnershipRepositoryImpl implements OwnershipRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

	@Override
	public void acquireItem(String ownerName, Long itemId) {

		Query query = entityManager.createNativeQuery("UPDATE items SET owner=? where id=?;");
		query.setParameter(1, ownerName);
		query.setParameter(2, itemId);
		query.executeUpdate();
	}


	@Override
	public void removeItemOwnership(Long itemId) {
		Query query = entityManager.createNativeQuery("UPDATE items SET owner=null where id=?;");
		query.setParameter(1, itemId);
		query.executeUpdate();		
	}

}
