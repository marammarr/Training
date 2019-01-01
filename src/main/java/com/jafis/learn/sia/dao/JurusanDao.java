package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.Jurusan;

public interface JurusanDao extends JpaRepository<Jurusan, Long>{
	
	@Query("SELECT m FROM Jurusan m where upper(m.nama) like %:name% OR lower(m.nama) like %:name% OR upper(m.kode) like %:name% OR lower(m.kode) like %:name%")
	public List<Jurusan> search(@Param("name") String name);
	
	@Query("SELECT m FROM Jurusan m WHERE m.isDeleted=FALSE or m.isDeleted IS NULL")
	public List<Jurusan> ambilDataYangAda();
}
