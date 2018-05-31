package com.jerald.udemy.restful.webservice.restservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userService;
	
	
	@GetMapping(path="/users")
	List<User> retrieveAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	Resource<User> retrieveUser(@PathVariable Integer id){
		User user = userService.findById(id);
		if(user == null){
			throw new UserNotFoundException("id-"+id);
		}
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		Resource<User> resource = new Resource<User>(user, linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/users")
	ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser =  userService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path="/users/{id}")
	void deleteUser(@PathVariable Integer id){
		User user = userService.deleteById(id);
		if(user == null){
			throw new UserNotFoundException("id-"+id);
		}
	}
}
