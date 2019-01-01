package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_KOTA", uniqueConstraints=@UniqueConstraint(columnNames= {"KDKOTA"}, name="UK_KDKOT"))
public class Kota {
	private Long id;
	private String kode;
	private String nama;
	private String kdprov;
	private boolean dihapus = false;

	@Id
	@GeneratedValue
	@Column(name="IDKOTA", nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="KDKOTA", length=100, nullable=false)
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	
	@Column(name="NAMAKOTA", length=100, nullable=false)
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	

	@Column(name="KDPROV",length=100, nullable=false)
	public String getKdprov() {
		return kdprov;
	}
	public void setKdprov(String kdprov) {
		this.kdprov = kdprov;
	}

	@Column(name="ISDELETED")
	public boolean isDihapus() {
		return dihapus;
	}
	public void setDihapus(boolean dihapus) {
		this.dihapus = dihapus;
	}
	
	
	
	
	
	
}