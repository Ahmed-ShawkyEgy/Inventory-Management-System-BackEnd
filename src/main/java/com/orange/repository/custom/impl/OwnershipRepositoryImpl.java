package com.orange.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.orange.repository.custom.OwnershipRepositoryCustom;

@Repository
@Transactional
@Component
public class OwnershipRepositoryImpl implements OwnershipRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

	@Override
	public void acquireItem(Long userId, Long itemId) {
		Query query = entityManager.createNativeQuery("INSERT INTO users_items (user_id,items_id) VALUES (?,?);");
		query.setParameter(1, userId);
		query.setParameter(2, itemId);
		query.executeUpdate();
	}

	@Override
	public void discardItem(Long userId, Long itemId) {
		Query query = entityManager.createNativeQuery("DELETE FROM users_items WHERE user_id = ? AND items_id = ?");
		query.setParameter(1, userId);
		query.setParameter(2, itemId);
		query.executeUpdate();		
	}

}
