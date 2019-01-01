package com.jafis.learn.sia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_KRS", uniqueConstraints=@UniqueConstraint(columnNames= {"KDKRS"}, name="UK_KRS_KD"))
public class KRS {
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
	
	@Column(name="DIHAPUS", nullable=false)
	private boolean dihapus;
	
	
	
}
