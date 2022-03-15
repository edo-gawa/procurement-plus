package com.procurementplus.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procurementplus.demo.dto.SsnDto;
import com.procurementplus.demo.dto.UserDto;
import com.procurementplus.demo.responsehandlers.ResponseHandler;
import com.procurementplus.demo.services.UserService;

@RestController()
@RequestMapping(value = "/v1")
public class UserController extends ResponseHandler {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/users")
	ResponseEntity<Object> addUser(@Valid @RequestBody UserDto body) {

		try {
			userService.addUser(body);
			return generateResponse("CREATED", 201, "Successfully added data!");
		} catch (Exception e) {
			return generateResponse(
					"Record with SSN < ".concat(
							body.getSocialNumbErSecurity().toString().concat(" > already exists in the system")),
					30002, "CONFLICT");
		}
	}

	@PutMapping(path = "/users")
	ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto body) {

		try {
			userService.updateUser(body);
			return generateResponse("UPDATED", 200, "Successfully update data!");
		} catch (Exception e) {
			return generateResponse(
					"No such resource with id < ".concat(
							body.getSocialNumbErSecurity().toString().concat(" > already exists in the system")),
					30000, "NOT_FOUND");
		}
	}

	@DeleteMapping(path = "/users/{id}")
	ResponseEntity<Object> deleteUser(@Valid SsnDto ssn) {

		try {
			userService.softDelete(ssn.getSsn());
			return generateResponse("DELETED", 200, "Successfully Delete data!");
		} catch (Exception e) {
			return generateResponse(
					"No such resource with id < ".concat(ssn.getId().concat(" > already exists in the system")), 30000,
					"NOT_FOUND");
		}
	}

	@GetMapping(path = "/users")
	ResponseEntity<Object> findAll() {
		try {
			return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return generateResponse("Not exists data in the system", 30000, "NOT_FOUND");
		}
	}

	@GetMapping(path = "/users/{id}")
	ResponseEntity<Object> findBySsn(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(userService.findBySSN(Integer.parseInt(id)), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return generateResponse("Not exists data in the system", 30000, "NOT_FOUND");
		}
	}

}
