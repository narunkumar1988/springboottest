package com.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.config.AsyncConfig.withTimeout;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.entity.Inventory;
import com.service.InventoryService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryControllerTest {

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private InventoryService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		RestAssuredMockMvc.webAppContextSetup(context);
		RestAssuredMockMvc.config().asyncConfig(withTimeout(10, TimeUnit.SECONDS));
	}

	@After
	public void tearDown() {
		RestAssuredMockMvc.reset();
	}

	@Test
	public void testGetAllInventory() {
		Mockito.when(service.getAllProduct()).thenReturn(new ArrayList<Inventory>());
		given().accept(ContentType.JSON).contentType(ContentType.JSON).when().get("/inventory/all").then()
				.apply(print()).statusCode(HttpStatus.OK.value());
	}

	@Test
	public void testGetInventory() {
		Mockito.when(service.getInventoryById(1l)).thenReturn(new Inventory());
		given().accept(ContentType.JSON).contentType(ContentType.JSON).when().get("/inventory/1").then().apply(print())
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void testUpdateInventory() {
		Inventory inv = Inventory.builder().productId(1l).availableQuantity(3l).totalQuantity(4l).build();
		Mockito.when(service.getInventoryById(inv.getProductId())).thenReturn(inv);
		Mockito.when(service.saveInventory(inv)).thenReturn(inv);
		given().body(inv).accept(ContentType.JSON).contentType(ContentType.JSON).when().put("/inventory").then()
				.apply(print()).statusCode(HttpStatus.OK.value());
	}

	@Test
	public void testAddInventory() {
		Inventory inv = Inventory.builder().productId(1l).availableQuantity(2l).minQuantity(1l).maxQuantity(2l)
				.totalQuantity(4l).returnQuantity(1l).manufacturer("Samsung").build();
		Mockito.when(service.saveInventory(inv)).thenReturn(inv);
		given().body(inv).accept(ContentType.JSON).contentType(ContentType.JSON).when().post("/inventory").then()
				.apply(print()).statusCode(HttpStatus.CREATED.value());
	}
}
