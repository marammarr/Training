package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_USER", uniqueConstraints=@UniqueConstraint(columnNames= {"USERNAME"}, name="UK_USERNAME"))
public class User {
	private String userid;
	private String password;
	private String username;
	private boolean isDeleted;
	
	
	@Id
	@Column(name="USERID", length=100, nullable=false)
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Column(name="PASSWORD", length=100, nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="USERNAME", length=100, nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="ISDELETED", nullable=false)
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
