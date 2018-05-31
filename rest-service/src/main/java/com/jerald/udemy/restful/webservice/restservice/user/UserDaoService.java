package com.jerald.udemy.restful.webservice.restservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	
	static{
		users.add(new User(1, "a", new Date()));
		users.add(new User(2, "b", new Date()));
		users.add(new User(3, "c", new Date()));
	}

	public List<User> findAll(){
		return users;
	}
	
	public User save(User user){
		if(user.getId() == null){
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findById(Integer id){
		for (User user : users) {
			if(user.getId().equals(id)){
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(Integer id){
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId().equals(id)){
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
