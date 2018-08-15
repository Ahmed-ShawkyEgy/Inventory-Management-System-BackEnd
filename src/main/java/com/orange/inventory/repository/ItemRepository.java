package com.orange.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.inventory.model.Item;
import com.orange.inventory.repository.custom.ItemRepositoryCustom;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long> , ItemRepositoryCustom{

}
