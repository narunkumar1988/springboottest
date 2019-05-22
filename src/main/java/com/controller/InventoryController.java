package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Inventory;
import com.service.InventoryService;

@RestController
@Validated
public class InventoryController {
	
	@Autowired
	private InventoryService service;
	
	@PostMapping(value="/inventory",produces = {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Inventory> addInventory(@RequestBody @Valid Inventory inv){
		inv = service.saveInventory(inv);
		return new ResponseEntity<>(inv,HttpStatus.CREATED);
	}
	
	@PutMapping(value="/inventory",produces = {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inv){
		Inventory fromDB = service.getInventoryById(inv.getProductId());
		fromDB.setAvailableQuantity(inv.getAvailableQuantity());
		fromDB.setManufacturer(inv.getManufacturer());
		fromDB = service.saveInventory(inv);
		return new ResponseEntity<>(fromDB,HttpStatus.OK);
	}
	
	@GetMapping(value="/inventory/all",produces = {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
	public List<Inventory> getInventory(){
		return service.getAllProduct();
	}
	
	@GetMapping(value="/inventory/{id}",produces = {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
	public Inventory getInventoryById(@PathVariable(name="id") Long productId){
		return service.getInventoryById(productId);
	}
	
	@DeleteMapping(value="/inventory/{id}",produces = {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> removeInventory(@PathVariable(name="id") Long productId){
		Inventory inv =service.getInventoryById(productId);
		service.removeProduct(inv);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
