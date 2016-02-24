package com.faraway.spring.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */
@Entity
@Table(name= "user", schema = "users", catalog = "")
public class UserEntity {

	@Id
	@Column(name="id")
	private int id;
	private String name;
	private Integer age;
	private Byte isAdmin;
	private Timestamp createdDate;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = true, length = 25)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return "id="+id+", name="+name+", age="+age;
	}

	@Basic
	@Column(name = "age", nullable = true)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Basic
	@Column(name = "isAdmin", nullable = true)
	public Byte getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Byte isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Basic
	@Column(name = "createdDate", nullable = false)
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return id == that.id &&
				Objects.equals(name, that.name) &&
				Objects.equals(age, that.age) &&
				Objects.equals(isAdmin, that.isAdmin) &&
				Objects.equals(createdDate, that.createdDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, age, isAdmin, createdDate);
	}
}
