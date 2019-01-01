package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.Menu;

public interface MenuDao extends JpaRepository<Menu, Long>{
	
	@Query("SELECT m FROM Menu m where upper(m.nama) like %:name% OR lower(m.nama) like %:name% OR upper(m.kode) like %:name% OR lower(m.kode) like %:name%")
	public List<Menu> search(@Param("name") String name);
	
	@Query("SELECT m FROM Menu m WHERE m.dihapus=FALSE or m.dihapus IS NULL")
	public List<Menu> ambilDataYangAda();
	
	@Query("SELECT m FROM Menu m where id=:id")
	public Menu checkMenuId(@Param("id") Long id);
	
}
