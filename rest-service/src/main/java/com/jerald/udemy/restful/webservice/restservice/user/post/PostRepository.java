package com.jerald.udemy.restful.webservice.restservice.user.post;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
