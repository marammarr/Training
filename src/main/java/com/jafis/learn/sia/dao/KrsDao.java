package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.KRS;

public interface KrsDao extends JpaRepository<KRS, Long>{
	
	@Query("SELECT m FROM KRS m WHERE m.dihapus=FALSE or m.dihapus IS NULL")
	public List<KRS> ambilDataYangAda();
	
	@Query("SELECT m FROM KRS m where id=:id")
	public KRS checkKRSId(@Param("id") Long id);
	
}
