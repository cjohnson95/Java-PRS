package com.PRS.Capstone.requestline;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RequestlineRepository extends JpaRepository<Requestline, Integer>{
	
	List<Requestline> findRequestlineByRequestId(int requestId);

	
	
	

	

}
