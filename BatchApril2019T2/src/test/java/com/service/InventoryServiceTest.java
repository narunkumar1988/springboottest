package com.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@WebMvcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestConfiguration.class)
public class InventoryServiceTest {

}
