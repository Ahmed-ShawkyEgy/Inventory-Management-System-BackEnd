package com.orange.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.inventory.model.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

}
