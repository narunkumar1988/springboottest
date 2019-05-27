package com.service;

import com.dao.InventoryRepository;
import com.entity.Inventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {


    InventoryServiceImpl inventoryService = new InventoryServiceImpl();

    @Mock
    InventoryRepository repository;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(inventoryService,"repo", this.repository);
    }

    @Test
    public void testSuccess(){
        Inventory inv = Inventory.builder().productId(1l).availableQuantity(2l).minQuantity(1l).maxQuantity(2l)
                .totalQuantity(4l).returnQuantity(1l).manufacturer("Samsung").build();
        Optional<Inventory> optInv = Optional.of(inv);
        Mockito.when(repository.save(inv)).thenReturn(inv);
        Inventory inv1 = inventoryService.saveInventory(inv);
        Assert.assertNotNull(inv1);
        Mockito.when(repository.findById(2l)).thenReturn(optInv);
        Assert.assertEquals(inv,inventoryService.getInventoryById(2l));
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
        Assert.assertNotNull(inventoryService.getAllProduct());
        // Assert.assertEquals(inv1.getManufacturer(),"Samsung");
    }
}
