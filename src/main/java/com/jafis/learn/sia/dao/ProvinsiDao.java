package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.Provinsi;

public interface ProvinsiDao extends JpaRepository<Provinsi, Long>{
	
	@Query("SELECT m FROM Provinsi m where upper(m.nama) like %:name% OR lower(m.nama) like %:name% OR upper(m.kode) like %:name% OR lower(m.kode) like %:name%")
	public List<Provinsi> search(@Param("name") String name);
	
	@Query("SELECT m FROM Provinsi m WHERE m.dihapus=false or m.dihapus IS NULL")
	public List<Provinsi> ambilDataYangAda();
	
	@Query("SELECT m FROM Provinsi m where id=:id")
	public Provinsi checkProvinsiId(@Param("id") Long id);
	
}
