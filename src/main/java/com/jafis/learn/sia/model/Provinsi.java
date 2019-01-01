package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_PROVINSI", uniqueConstraints=@UniqueConstraint(columnNames= {"KDPROV"}, name="UK_KD"))
public class Provinsi {
	private Long id;
	private String kode;
	private String nama;
	private boolean dihapus = false;

	@Id
	@GeneratedValue
	@Column(name="IDPROV", nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="KDPROV", length=100, nullable=false)
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	
	@Column(name="NAMAPROV", length=100, nullable=false)
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	

	@Column(name="ISDELETED")
	public boolean isDihapus() {
		return dihapus;
	}
	public void setDihapus(boolean dihapus) {
		this.dihapus = dihapus;
	}
	
	
	
}