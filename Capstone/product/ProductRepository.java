package com.PRS.Capstone.product;

import org.springframework.data.jpa.repository.JpaRepository;





public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	
	
	//i thought the repo was meant only for methods that were not defined automatically in the interface. Why am I having an error about 
	

}
