package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InventoryRepository;
import com.entity.Inventory;

@Service("service")
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	InventoryRepository repo;

	@Override
	public List<Inventory> getAllProduct() {
		return repo.findAll();
	}

	@Override
	public Inventory getInventoryById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Inventory saveInventory(Inventory inv) {
		return repo.save(inv);
	}

	@Override
	public void removeProduct(Inventory inv) {
		repo.delete(inv);
	}
	
	

}
