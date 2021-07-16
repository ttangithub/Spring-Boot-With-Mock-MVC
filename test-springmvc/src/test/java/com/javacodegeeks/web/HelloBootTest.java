package com.javacodegeeks.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloBootTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void whenNoRequestParam_returnDefault() throws Exception {
		this.mockMvc.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hello world!")))
			.andDo(print());
	}

	@Test
	void whenRequestParam_returnCustom() throws Exception {
		this.mockMvc.perform(
				get("/hello")
				.queryParam("name", "JCG!")
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hello JCG!")))
			.andDo(print());
	}

}
