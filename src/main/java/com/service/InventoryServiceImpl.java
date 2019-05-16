package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InventoryRepository;
import com.entity.Inventory;
import com.exception.ProductNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service("service")
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	InventoryRepository repo;

	@Override
	@HystrixCommand(fallbackMethod="defaultAllProduct")
	public List<Inventory> getAllProduct() {
		return repo.findAll();
	}

	@Override
	//@HystrixCommand(fallbackMethod="defaultGetById")
	public Inventory getInventoryById(Long id) {
		Optional<Inventory> optInv = repo.findById(id);
		if(!optInv.isPresent())
			throw new ProductNotFoundException("Product not found");
		return optInv.get();
	}

	@Override
	@HystrixCommand(fallbackMethod="defaultSave")
	public Inventory saveInventory(Inventory inv) {
		return repo.save(inv);
	}

	@Override
	public void removeProduct(Inventory inv) {
		repo.delete(inv);
	}
	
	public List<Inventory> defaultAllProduct() {
		return null;
	}
	
	public Inventory defaultGetById(Long id) {
		return null;
	}
	
	public Inventory defaultSave(Inventory inv) {
		return null;
	}
}
