package com.classifier.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.classifier.domain.User;
import com.classifier.dto.UserNewDTO;
import com.classifier.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<User> find(@PathVariable Integer id) {
		User user = service.find(id);
		return ResponseEntity.ok(user);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> insert(@RequestBody @Valid UserNewDTO dto) {
		User user = service.fromDTO(dto);
		user = service.insert(user);
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getusers() {
		List<User> obj = service.getUsers();
		return ResponseEntity.ok(obj);
	}
}
