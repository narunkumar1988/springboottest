package com.dao;

import com.entity.Inventory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class InventoryRepositoryTest {

    @Autowired
    InventoryRepository repo;

    @Test
    public void testCreate(){
        Inventory inv = Inventory.builder().productId(1l).availableQuantity(2l).minQuantity(1l).maxQuantity(2l)
                .totalQuantity(4l).returnQuantity(1l).manufacturer("Samsung").build();
        repo.save(inv);
        Inventory getInv = repo.findById(1l).get();
        Assert.assertEquals(inv.getAvailableQuantity(),getInv.getAvailableQuantity());
    }
}
