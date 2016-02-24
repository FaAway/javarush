package com.faraway.spring.dao;

import com.faraway.spring.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(UserEntity p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("UserEntity saved successfully, UserEntity Details="+p);
	}

	@Override
	public void updatePerson(UserEntity p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("UserEntity updated successfully, UserEntity Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserEntity> personsList = session.createQuery("from UserEntity").list();
		for(UserEntity p : personsList){
			logger.info("UserEntity List::"+p);
		}
		return personsList;
	}

	@Override
	public UserEntity getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		UserEntity p = (UserEntity) session.load(UserEntity.class, new Integer(id));
		logger.info("UserEntity loaded successfully, UserEntity details="+p);
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserEntity p = (UserEntity) session.load(UserEntity.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("UserEntity deleted successfully, person details="+p);
	}

}
