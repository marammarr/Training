package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_KRS", uniqueConstraints=@UniqueConstraint(columnNames= {"KDKRS"}, name="UK_KRS_KD"))
public class Matakuliah {
	@Id
	@GeneratedValue
	@Column(name="ID", nullable=false)
	private Integer id;
	
	@Column(name="KDMATKUL", nullable=false)
	private String kdMatkul;
	
	@Column(name="NIM", nullable=false)
	private String nama;
	
	@Column(name="SKS", nullable=false)
	private String sks;
	
	@Column(name="DIHAPUS", nullable=false)
	private boolean dihapus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKdMatkul() {
		return kdMatkul;
	}

	public void setKdMatkul(String kdMatkul) {
		this.kdMatkul = kdMatkul;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getSks() {
		return sks;
	}

	public void setSks(String sks) {
		this.sks = sks;
	}

	public boolean isDihapus() {
		return dihapus;
	}

	public void setDihapus(boolean dihapus) {
		this.dihapus = dihapus;
	}
	
	
	
}
