package com.jerald.udemy.restful.webservice.restservice.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.jerald.udemy.restful.webservice.restservice.user.post.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("All information about the user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message="name should have more than 1 letter")
	@ApiModelProperty(notes="name should have atleast two characters")
	private String name;
	
	@ApiModelProperty(notes="Date should be a past value")
	@Past(message="Date should be a past value")
	private Date birthday;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthday=%s]", id, name, birthday);
	}
	
	
}
