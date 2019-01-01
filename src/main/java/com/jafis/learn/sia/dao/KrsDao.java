package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.Krs;

public interface KrsDao extends JpaRepository<Krs, Long>{
	
	@Query("SELECT m FROM Krs m WHERE m.dihapus=FALSE or m.dihapus IS NULL")
	public List<Krs> ambilDataYangAda();
	
	@Query("SELECT m FROM Krs m where id=:id")
	public Krs checkKrsId(@Param("id") Long id);
	
	@Query("SELECT m FROM Krs m WHERE nim LIKE %:word%")
	public List<Krs> search(@Param("word") String word);
}
