package com.service;

import java.util.List;

import com.entity.Inventory;


public interface InventoryService {
	
	public List<Inventory> getAllProduct();
	public Inventory getInventoryById(Long id);
	public Inventory saveInventory(Inventory inv);
	public void removeProduct(Inventory inv);

}
