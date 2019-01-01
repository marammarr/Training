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

import com.jafis.learn.sia.dao.KotaDao;
import com.jafis.learn.sia.model.Kota;
import com.jafis.learn.sia.object.ResponseWeb;

@RestController
@RequestMapping(value= {"/kota","/api/kota"})
public class KotaController {
	@Autowired
	private KotaDao kotaDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//GET ID
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Kota> get(@PathVariable Long id){
		try {
			Kota jur = this.kotaDao.findOne(id);
			return new ResponseEntity<>(jur,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//DELETE
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			Kota u = this.kotaDao.checkKotaId(id);
			ResponseWeb out = new ResponseWeb();
			if(u!=null) {
				u.setDihapus(true);
				this.kotaDao.save(u);
				out.setResponse("Data Terhapus");
				out.setParameterNilai(1);
				return new ResponseEntity<>(out,HttpStatus.OK);
			}else {
				out.setResponse("Data gagal dihapus : Tak ada userid seperti itu");
				out.setParameterNilai(0);
				return new ResponseEntity<>(out,HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	//AMBIL SEMUA YG TAK DIDELETE
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Kota>> getAll() {
		try {
			List<Kota> jur = this.kotaDao.ambilDataYangAda();//this.kotaDao.findAll();
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Kota jur) {
		try {
			jur.setDihapus(false);
			ResponseWeb out = new ResponseWeb();
			out.setResponse(this.kotaDao.save(jur)+"");
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
	public ResponseEntity<?> update(@RequestBody Kota kot) {
		try {
			this.kotaDao.save(kot);
			return new ResponseEntity<>(kot, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//SEARCH
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Kota>> search(@PathVariable String name) {
		try {
			List<Kota> jurList = this.kotaDao.search(name);
			System.out.println(name);
			return new ResponseEntity<>(jurList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	

}
