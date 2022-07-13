package com.qa.dfewaiter.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.qa.dfewaiter.entities.Waiter;
import com.qa.dfewaiter.repos.waiterRepos;




@Service
public class WaiterService {
private waiterRepos repo;
	
	public WaiterService(waiterRepos repo) {
		this.repo = repo;
	}

	public List<Waiter> readAll() {
		// TODO Auto-generated method stub
		//GET - READ
		//ReadAll
		return this.repo.findAll();
	}
	//ReadByID

	public Waiter readById(long id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id).get(); 
	}
	//ReadByFirstName
		public List<Waiter> readByFirstName(String firstName) {
			return this.repo.findCustomerByFirstName(firstName);
		}
		
		//POST - CREATE
		public Waiter create(Waiter waiter) {
			return this.repo.saveAndFlush(waiter);
		}
		
		//PUT - UPDATE
		public Waiter update(long id, Waiter waiter) {
			//1) Get the existing entry.
			Waiter existing = this.repo.findById(id).get();
			
			//2) Change the existing entry, using our new customer object above.
			existing.setFirstName(waiter.getFirstName());
			existing.setLastName(waiter.getLastName());
			existing.setEmail(waiter.getEmail());
			//3) Save the entry back into the Database.
			return this.repo.saveAndFlush(existing);
		}
		
		
		//DELETE - DELETE
		public boolean delete(long id) {
			this.repo.deleteById(id);
			
			return !this.repo.existsById(id); // this should be false. If it's true, then the delete failed, somehow.
		}
			

}
