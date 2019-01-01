package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_KRS", uniqueConstraints=@UniqueConstraint(columnNames= {"KDKRS"}, name="UK_KRS_KD"))
public class Krs {
	@Id
	@GeneratedValue
	@Column(name="ID", nullable=false)
	private Integer id;
	
	@Column(name="KDKRS", nullable=false)
	private String kode;
	
	@Column(name="NIM", nullable=false)
	private String nim;
	
	@Column(name="KDMATKUL", nullable=false)
	private String kdMatkul;
	
	@Column(name="SKS", nullable=false)
	private String sks;
	
	@Column(name="SEMESTER", nullable=false)
	private String semester;
	
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

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getKdMatkul() {
		return kdMatkul;
	}

	public void setKdMatkul(String kdMatkul) {
		this.kdMatkul = kdMatkul;
	}

	public String getSks() {
		return sks;
	}

	public void setSks(String sks) {
		this.sks = sks;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public boolean isDihapus() {
		return dihapus;
	}

	public void setDihapus(boolean dihapus) {
		this.dihapus = dihapus;
	}
	
	
	
}
