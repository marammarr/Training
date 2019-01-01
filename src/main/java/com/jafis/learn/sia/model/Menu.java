package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_MENU", uniqueConstraints=@UniqueConstraint(columnNames= {"KDMENU"}, name="UK_MENU_KD"))
public class Menu {
	@Id
	@GeneratedValue
	@Column(name="ID", nullable=false)
	private Integer id;
	
	@Column(name="KDMENU", nullable=false)
	private String kode;
	
	@Column(name="NAMAMENU", nullable=false)
	private String nama;
	
	@Column(name="ROLEMENU", nullable=false)
	private String role;
	
	@Column(name="DIHAPUS", nullable=false)
	private boolean dihapus;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean getDihapus() {
		return dihapus;
	}
	public void setDihapus(boolean dihapus) {
		this.dihapus = dihapus;
	}
	
	
	
}
