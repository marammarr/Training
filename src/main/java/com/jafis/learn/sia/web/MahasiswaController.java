/**
 * 
 */
package com.jafis.learn.sia.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jafis.learn.sia.dao.MahasiswaDao;
import com.jafis.learn.sia.model.Mahasiswa;
import com.jafis.learn.sia.object.ResponseWeb;

/**
 * @author Awiyanto Ajisasongko
 *
 */
@RestController
@RequestMapping(value= {"/mahasiswa","/api/mahasiswa"})
public class MahasiswaController {
	@Autowired
	private MahasiswaDao mahasiswaDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//AMBIL PAKE ID
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Mahasiswa> get(@PathVariable Long id) {
		try {
			Mahasiswa mhs = this.mahasiswaDao.findOne(id);
			return new ResponseEntity<>(mhs, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//DELETE
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		//this.userDao.delete(id);
		Mahasiswa u = this.mahasiswaDao.checkMahasiswaId(id);
		ResponseWeb out = new ResponseWeb();
		if(u!=null) {
			u.setDeleted(true);
			this.mahasiswaDao.save(u);
			out.setResponse("Data Terhapus");
			out.setParameterNilai(1);
			return new ResponseEntity<>(out,HttpStatus.OK);
		}else {
			out.setResponse("Data gagal dihapus : Tak ada userid seperti itu");
			out.setParameterNilai(0);
			return new ResponseEntity<>(out,HttpStatus.OK);
		}
	}

	//AMBIL SEMUA
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Mahasiswa>> getAll() {
		try {
			List<Mahasiswa> mhs = this.mahasiswaDao.ambilDataYangAda();
			return new ResponseEntity<>(mhs, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Mahasiswa mhs) {
		try {
			mhs.setDeleted(false);
			ObjectMapper json = new ObjectMapper();
			ResponseWeb out = new ResponseWeb();
			out.setParameterNilai(1);
			out.setResponse(json.writeValueAsString(this.mahasiswaDao.save(mhs))); //jalanin
			System.out.println("TESS "+json.writeValueAsString(mhs));
			return new ResponseEntity<>(out, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}	

	//UPDATE
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<Mahasiswa> update(@RequestBody Mahasiswa mhs) {
		try {
			this.mahasiswaDao.save(mhs);
			return new ResponseEntity<>(mhs, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//SEARCH
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Mahasiswa>> search(@PathVariable String name) {
		try {
			List<Mahasiswa> mhsList = this.mahasiswaDao.search(name);
			System.out.println(name);
			return new ResponseEntity<>(mhsList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}
