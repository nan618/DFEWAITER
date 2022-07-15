package com.qa.dfewaiter.controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.dfewaiter.entities.Waiter;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")

public class WaiterControllerTest {
	@Autowired
	private MockMvc mvc; // used for sending mock requests
	
	@Autowired
	private ObjectMapper mapper; // used for converting objects to JSON
	
	@Test
	public void createTest() throws Exception {
		Waiter entry = new Waiter("Nan", "Ji", "ji@qa.com");
		String entryAsJSON = mapper.writeValueAsString(entry);

		Waiter result = new Waiter(2L, "Nan", "Ji", "ji@qa.com");
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(post("/waiter/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(content().json(resultAsJSON));

	}
	
	@Test
	public void readAllTest() throws Exception {
		// Setting up my expected output object
		List<Waiter> output = new ArrayList<>();
		Waiter entry = new Waiter(1L, "Nan", "Ji", "nan@qa.com");
		output.add(entry);
		// Convert my expected output to JSON
		String outputAsJSON = mapper.writeValueAsString(output);
		
		mvc.perform(get("/waiter/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(outputAsJSON));

	}
	
	@Test
	public void readByIdTest() throws Exception {
		Waiter entry = new Waiter(1L, "Nan", "Ji", "nan@qa.com");
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		
		mvc.perform(get("/waiter/readById/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(entryAsJSON));
	}
	
	@Test
	public void updateTest() throws Exception {
		Waiter entry = new Waiter("jennifer", "Ji", "nan@qa.com");
		Waiter result = new Waiter(1L, "jennifer", "Ji", "nan@qa.com");
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		String resultAsJSON = this.mapper.writeValueAsString(result);
		
		mvc.perform(put("/waiter/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(entryAsJSON))
			.andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/waiter/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("true"));
	}
	
}


