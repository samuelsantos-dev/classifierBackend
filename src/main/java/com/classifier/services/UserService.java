package com.classifier.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classifier.domain.User;
import com.classifier.dto.UserNewDTO;
import com.classifier.repositories.UserRepository;
import com.classifier.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository rep;

	public User find(Integer id) {
		Optional<User> opt = rep.findById(id);

		return opt.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}

	public User insert(User user) {
		user.setId(null);
		return rep.save(user);
	}

	public User fromDTO(UserNewDTO dto) {
		User user = new User(null, dto.getName(), dto.getCpf(), 1, dto.getEmail(), dto.getEmail(), dto.getPassword());
		return user;
	}
	
	public List<User> getUsers(){
		return rep.findAll();
	}

}
