package com.faraway.spring.service;

import com.faraway.spring.dao.UserDAO;
import com.faraway.spring.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void addPerson(UserEntity p) {
		p.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
		this.userDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(UserEntity p) {
		p.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
		this.userDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<UserEntity> listPersons() {
		return this.userDAO.listPersons();
	}

	@Override
	@Transactional
	public List<UserEntity> listPersons(int page) {
		List<UserEntity> list = this.userDAO.listPersons();
		List<UserEntity> result = new ArrayList<UserEntity>();
		for (int i = page * 10; i < (page + 1) * 10; i++) {
			if (i < list.size())
				result.add(list.get(i));
		}
		return result;
	}

	@Override
	@Transactional
	public UserEntity getPersonById(int id) {
		return this.userDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.userDAO.removePerson(id);
	}

}
