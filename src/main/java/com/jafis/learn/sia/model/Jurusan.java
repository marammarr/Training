package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_JURUSAN", uniqueConstraints=@UniqueConstraint(columnNames= {"JURKODE"}, name="UK_JUR_KD"))
public class Jurusan {

	@Id
	@GeneratedValue
	@Column(name="JURID", nullable=false)
	private Long id;

	@Column(name="JURKODE", length=5, nullable=false)
	private String kode;

	@Column(name="JURNAMA", length=100, nullable=false)
	private String nama;
	
	@Column(name="ISDELETED")
	private boolean isDeleted = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
