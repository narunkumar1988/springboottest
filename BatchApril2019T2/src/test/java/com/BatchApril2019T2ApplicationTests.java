package com;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.dao.InventoryRepository;
import com.entity.Inventory;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BatchApril2019T2Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BatchApril2019T2ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private InventoryRepository invRepo;
	
	@After
    public void resetDb() {
		invRepo.deleteAll();
    }
	
	@Test
	public void contextLoads() throws Exception {
		Inventory inv = Inventory.builder().productId(1l).availableQuantity(2l).minQuantity(1l).maxQuantity(2l)
				.totalQuantity(4l).returnQuantity(1l).manufacturer("Samsung").build();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(inv);
		this.mockMvc.perform(post("/inventory").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(mapper.writeValueAsString(inv)));
		this.mockMvc.perform(get("/inventory/1").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())//.andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$.manufacturer", is(inv.getManufacturer())));
		inv.setManufacturer("LG");
		mapper = new ObjectMapper();
		json = mapper.writeValueAsString(inv);
		System.out.println(json);
		this.mockMvc.perform(put("/inventory").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(mapper.writeValueAsString(inv)));
		this.mockMvc.perform(get("/inventory/1").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.manufacturer", is("LG")));
		this.mockMvc.perform(delete("/inventory/1").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent());
		
	}

}
