package com.jerald.udemy.restful.webservice.restservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

import com.jerald.udemy.restful.webservice.restservice.user.post.Post;
import com.jerald.udemy.restful.webservice.restservice.user.post.PostRepository;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path="jpa/users")
	List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="jpa/users/{id}")
	Resource<User> retrieveUser(@PathVariable Integer id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id-"+id);
		}
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		Resource<User> resource = new Resource<User>(user.get(), linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("jpa/users")
	ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser =  userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path="jpa/users/{id}")
	void deleteUser(@PathVariable Integer id){
		userRepository.deleteById(id);
	}
	
	@GetMapping(path="jpa/users/{id}/posts")
	List<Post> retrievePosts(@PathVariable Integer id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id-"+id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	ResponseEntity<Object> createPost( @PathVariable("id")Integer userId, @RequestBody Post post){
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()){
			throw new UserNotFoundException("id-"+userId);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
}
