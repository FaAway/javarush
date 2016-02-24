package com.faraway.spring.service;

import com.faraway.spring.model.UserEntity;

import java.util.List;

public interface UserService {

	public void addPerson(UserEntity p);
	public void updatePerson(UserEntity p);
	public List<UserEntity> listPersons();
	public List<UserEntity> listPersons(int page);
	public UserEntity getPersonById(int id);
	public void removePerson(int id);
	
}
