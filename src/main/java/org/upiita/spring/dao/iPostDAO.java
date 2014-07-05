package org.upiita.spring.dao;

import java.util.List;

import org.upiita.spring.entity.Post;

public interface iPostDAO {

	public abstract Post getPost(int id);

	public abstract Integer guardar(Post post);

	List<Post> getSimilarPost(String bus);

	List<Post> getDifferentPost(String bus);

}