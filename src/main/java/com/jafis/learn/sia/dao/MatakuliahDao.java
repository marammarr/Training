package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.Matakuliah;

public interface MatakuliahDao extends JpaRepository<Matakuliah, Long>{
	
	@Query("SELECT m FROM Matakuliah m WHERE m.dihapus=FALSE or m.dihapus IS NULL")
	public List<Matakuliah> ambilDataYangAda();
	
	@Query("SELECT m FROM Matakuliah m where id=:id")
	public Matakuliah checkMatakuliahId(@Param("id") Long id);
	
	@Query("SELECT m FROM Matakuliah m WHERE nama LIKE %:word%")
	public List<Matakuliah> search(@Param("word") String word);
}
