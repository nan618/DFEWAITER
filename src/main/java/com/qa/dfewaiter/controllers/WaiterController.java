package com.qa.dfewaiter.controllers;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.dfewaiter.entities.Waiter;
import com.qa.dfewaiter.services.WaiterService;

// Handle incoming HTTP requests and send responses
// Uses JSON data
@RestController
@RequestMapping("/waiter") // adds a prefix to the request URLs
public class WaiterController {

	private WaiterService service;
	
	// Dependency injection
	public WaiterController(WaiterService service) {
		this.service = service;
	}
	
	//GET - READ
	//ReadAll
	@GetMapping("/readAll")
	public List<Waiter> readAll() {
		return this.service.readAll();
	}
	
	//ReadByID
	@GetMapping("/readById/{id}")
	public Waiter readById(@PathVariable long id) {
		return this.service.readById(id);
	}
	
	//ReadByFirstName
	@GetMapping("/readByFirstName/{firstName}")
	public List<Waiter> readByFirstName(@PathVariable String firstName) {
		return ((WaiterController) this.service).readByFirstName(firstName);
	}
	
	//POST - CREATE
	@PostMapping("/create") // localhost:8080/customer/create
	public Waiter create(@RequestBody Waiter waiter ) {
		return this.service.create(waiter);
	}
	
	//PUT - UPDATE
	@PutMapping("/update/{id}")
	public Waiter update(@PathVariable long id, @RequestBody Waiter waiter ) {
		return this.service.update(id, waiter);
	}
	
	//DELETE - DELETE
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable long id) {
		return this.service.delete(id);
	}
}