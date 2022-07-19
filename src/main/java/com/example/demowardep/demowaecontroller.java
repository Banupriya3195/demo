package com.example.demowardep;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class demowaecontroller {
	@Autowired
	demowarrepo firstrep;
	
	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> getdata() {
		try {
			List<Employee> list = firstrep.findAll();
			System.out.println("hello");
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/emp")
	public ResponseEntity<Employee> save(@RequestBody Employee detail) {
		try {
			System.out.println("hello");
			return new ResponseEntity<>(firstrep.save(detail), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getSingleEmployee(@PathVariable Long id) {
		Optional<Employee> detail = firstrep.findById(id);
		if (detail.isPresent()) {
			return new ResponseEntity<Employee>(detail.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/emp/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee detail) {
		
		try {
			return new ResponseEntity<Employee>(firstrep.save(detail), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/emp/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
		try {
			Optional<Employee> detail = firstrep.findById(id);
			if (detail.isPresent()) {
				firstrep.delete(detail.get());
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/helo")
	public String hello(){
		return "hello";
		
	}


}
