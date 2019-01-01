package com.jafis.learn.sia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jafis.learn.sia.model.Mahasiswa;

/**
 * @author Awiyanto Ajisasongko
 */
public interface MahasiswaDao extends JpaRepository<Mahasiswa, Long> {
	
	//@Query("SELECT m FROM Mahasiswa m WHERE m.nama LIKE '%:name%'")
	//public List<Mahasiswa> search(@Param("name") String m);
	@Query("SELECT m FROM Mahasiswa m where upper(m.nama) like %:name% OR lower(m.nama) like %:name%")
	public List<Mahasiswa> search(@Param("name") String name);

	@Query("SELECT m FROM Mahasiswa m WHERE isdeleted=false")
	public List<Mahasiswa> ambilDataYangAda();
	
	@Query("SELECT m FROM Mahasiswa m where id=:id")
	public Mahasiswa checkMahasiswaId(@Param("id") Long id);
	
}
