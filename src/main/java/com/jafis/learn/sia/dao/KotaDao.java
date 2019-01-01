package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.Kota;

public interface KotaDao extends JpaRepository<Kota, Long>{
	
	@Query("SELECT m FROM Kota m where upper(m.nama) like %:name% OR lower(m.nama) like %:name% OR upper(m.kode) like %:name% OR lower(m.kode) like %:name%")
	public List<Kota> search(@Param("name") String name);
	
	@Query("SELECT m FROM Kota m WHERE m.dihapus=FALSE or m.dihapus IS NULL")
	public List<Kota> ambilDataYangAda();
	
	@Query("SELECT m FROM Kota m where id=:id")
	public Kota checkKotaId(@Param("id") Long id);
	
}
