package com.qa.dfewaiter.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.dfewaiter.entities.Waiter;

public interface waiterRepos extends JpaRepository<Waiter, Long> {
	
	// SELECT * FROM customer WHERE first_name = firstName;
	List<Waiter> findCustomerByFirstName(String firstName);
	
}
	


