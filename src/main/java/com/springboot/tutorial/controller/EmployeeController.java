package com.springboot.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.tutorial.exceptions.ResourceNotFoundException;
import com.springboot.tutorial.model.Employee;
import com.springboot.tutorial.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@Validated @RequestBody Employee employee){
		Employee employee2 = employeeRepository.save(employee);
		return new ResponseEntity<Employee>(employee2,HttpStatus.CREATED);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found with given EmployeeId : "+employeeId));
		return new ResponseEntity<Employee>(employee,HttpStatus.FOUND);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long employeeId, @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found with given EmployeeId : "+employeeId));
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		Employee updatedEmployee = employeeRepository.save(employee);	
		return new ResponseEntity<Employee>(updatedEmployee,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value="id") Long employeeId){
		employeeRepository.deleteById(employeeId);
		return new ResponseEntity<String>("Employee Deleted",HttpStatus.OK);
	}
	
	
}
