package com.PRS.Capstone.requestline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PRS.Capstone.product.ProductRepository;
import com.PRS.Capstone.request.RequestRepository;






@CrossOrigin
@RestController
@RequestMapping("/api/requestlines")
public class RequestlinesController {
	
	private static final Integer requestId = null;
	@Autowired
	private RequestlineRepository reqliRepo;
	@Autowired
	private RequestRepository reqRepo;
	@Autowired ProductRepository prodRepo;
	
@GetMapping
public ResponseEntity<Iterable<Requestline>>GetAll() {
	var requestline = reqliRepo.findAll();
	return new ResponseEntity<Iterable<Requestline>>(requestline, HttpStatus.OK);
	

	}

@GetMapping("{id}")										
public ResponseEntity<Requestline> GetById(@PathVariable int id ) {   
	
	var requestline = reqliRepo.findById(id);
	if(requestline.isEmpty()) {
		return new ResponseEntity<>(HttpStatus. NOT_FOUND);										
	}														
	return new ResponseEntity<Requestline>(requestline.get(), HttpStatus.OK);
	}

@PostMapping
public ResponseEntity <Requestline> Insert(@RequestBody Requestline requestline) throws Exception {
	
	if(requestline == null) {
		return new ResponseEntity<>(HttpStatus. BAD_REQUEST);
	}

	requestline.setId(0);
	var newRequestline = reqliRepo.save(requestline);
	reqliRepo.save(requestline);
	RecalculateRequestTotal(requestline.getRequest().getId());
	return new ResponseEntity<>(newRequestline, HttpStatus.CREATED);
	}
	

	

@PutMapping("{id}")
public ResponseEntity Update(@PathVariable int id, @RequestBody Requestline requestline) throws Exception {
	if(requestline.getId() != id) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	 reqliRepo.save(requestline);
	 RecalculateRequestTotal(requestline.getRequest().getId());
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	
	


@DeleteMapping("{id}")
public ResponseEntity Delete(@PathVariable int id) throws Exception {
var requestline= reqliRepo.findById(id);
if (requestline.isEmpty()){
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

reqliRepo.deleteById(id);
RecalculateRequestTotal(requestline.get().getRequest().getId());  
return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	

	private void RecalculateRequestTotal(int id) throws Exception {
	
	var optRequest = reqRepo.findById(id);
	if(optRequest.isEmpty())
	{
		throw new Exception("Request id is invalid!");
	}
	var request = optRequest.get();
	var requestlines = reqliRepo.findRequestlineByRequestId(request.getId());
	var total = 0.0;
	for(var reqLine : requestlines) {
		if(reqLine.getProduct().getPrice()== 0) {
			var product = prodRepo.findById(reqLine.getProduct().getId()).get();
			reqLine.setProduct(product);
		}
		total += reqLine.getQuantity() * reqLine.getProduct().getPrice();
	}
	
	request.setTotal(total);
	reqRepo.save(request);
	
	}

}