package com.orange.inventory.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.orange.inventory.model.User;
import com.orange.inventory.repository.custom.OwnershipRepositoryCustom;

@Repository
@Transactional
@Component
public class OwnershipRepositoryImpl implements OwnershipRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

	@Override
	public void acquireItem(Long userId, Long itemId) {
		Query query = entityManager.createNativeQuery(
				"INSERT INTO users_items (user_id,items_id) VALUES (?,?);");
		query.setParameter(1, userId);
		query.setParameter(2, itemId);		
		query.executeUpdate();
		
		query = entityManager.createNativeQuery("UPDATE items SET owner=? where id=?;");
		query.setParameter(1, userId);
		query.setParameter(2, itemId);
		query.executeUpdate();
	}

	@Override
	public void discardItem(Long userId, Long itemId) {
		Query query = entityManager.createNativeQuery(
				"DELETE FROM users_items WHERE user_id = ? AND items_id = ?;");
		query.setParameter(1, userId);
		query.setParameter(2, itemId);
		query.executeUpdate();		
		
		query = entityManager.createNativeQuery("UPDATE items SET owner=null where id=?;");		
		query.setParameter(1, itemId);
		query.executeUpdate();		
	}

	@Override
	public void discardAllItemsByUser(Long userId) {
		Query query = entityManager.createNativeQuery("DELETE FROM users_items WHERE user_id = ?");
		query.setParameter(1, userId);
		query.executeUpdate();		
		
		query = entityManager.createNativeQuery("UPDATE items SET owner=null where owner=?;");
		query.setParameter(1, userId);
		query.executeUpdate();		
		
	}

	@Override
	public void removeItemOwnership(Long itemId) {
		Query query = entityManager.createNativeQuery("DELETE FROM users_items WHERE items_id = ?");
		query.setParameter(1, itemId);
		query.executeUpdate();		
		
		
		query = entityManager.createNativeQuery("UPDATE items SET owner=null where id=?;");
		query.setParameter(1, itemId);
		query.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User>findOwner(Long itemId) {
		Query query = entityManager.createNativeQuery("SELECT u.* FROM users_items ui , users u WHERE u.id = ui.user_id AND items_id=?");
		query.setParameter(1, itemId);
		return (List<User>) query.getResultList();
	}

}
