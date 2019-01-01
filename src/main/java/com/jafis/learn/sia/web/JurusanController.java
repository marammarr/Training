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

import com.jafis.learn.sia.dao.JurusanDao;
import com.jafis.learn.sia.model.Jurusan;
import com.jafis.learn.sia.object.ResponseWeb;

@RestController
@RequestMapping(value= {"/jurusan","/api/jurusan"})
public class JurusanController {
	@Autowired
	private JurusanDao jurusanDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//GET ID
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Jurusan> get(@PathVariable Long id){
		try {
			Jurusan jur = this.jurusanDao.findOne(id);
			return new ResponseEntity<>(jur,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//DELETE
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Jurusan> delete(@PathVariable Long id) {
		try {
			this.jurusanDao.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	//AMBIL SEMUA YG TAK DIDELETE
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Jurusan>> getAll() {
		try {
			List<Jurusan> jur = this.jurusanDao.ambilDataYangAda();//this.jurusanDao.findAll();
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Jurusan jur) {
		try {
			jur.setDeleted(false);
			ResponseWeb out = new ResponseWeb();
			out.setResponse(this.jurusanDao.save(jur)+"");
			out.setParameterNilai(1);
			return new ResponseEntity<>(out, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}	

	//UPDATE
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<Jurusan> update(@RequestBody Jurusan jur) {
		try {
			this.jurusanDao.save(jur);
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//SEARCH
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Jurusan>> search(@PathVariable String name) {
		try {
			List<Jurusan> jurList = this.jurusanDao.search(name);
			System.out.println(name);
			return new ResponseEntity<>(jurList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	

}
