package com.faraway.spring.dao;

import com.faraway.spring.model.UserEntity;

import java.util.List;

public interface UserDAO {

	public void addPerson(UserEntity p);
	public void updatePerson(UserEntity p);
	public List<UserEntity> listPersons();
	public UserEntity getPersonById(int id);
	public void removePerson(int id);
}
