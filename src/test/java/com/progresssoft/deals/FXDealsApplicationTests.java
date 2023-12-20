package com.progresssoft.deals;

import com.progresssoft.deals.controller.FXDealController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class FXDealsApplicationTests {

	@Autowired
	private FXDealController controller;
	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}
}
