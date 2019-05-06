package com.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Inventory;

@Repository("invRepo")
@Transactional
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

}
