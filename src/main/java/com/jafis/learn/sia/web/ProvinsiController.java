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

import com.jafis.learn.sia.dao.ProvinsiDao;
import com.jafis.learn.sia.model.Provinsi;
import com.jafis.learn.sia.object.ResponseWeb;

@RestController
@RequestMapping(value= {"/provinsi","/api/provinsi"})
public class ProvinsiController {
	@Autowired
	private ProvinsiDao provinsiDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//GET ID
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Provinsi> get(@PathVariable Long id){
		try {
			Provinsi jur = this.provinsiDao.findOne(id);
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
			Provinsi u = this.provinsiDao.checkProvinsiId(id);
			ResponseWeb out = new ResponseWeb();
			if(u!=null) {
				u.setDihapus(true);
				this.provinsiDao.save(u);
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
	public ResponseEntity<List<Provinsi>> getAll() {
		try {
			List<Provinsi> jur = this.provinsiDao.ambilDataYangAda();//this.provinsiDao.findAll();
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Provinsi jur) {
		try {
			jur.setDihapus(false);
			ResponseWeb out = new ResponseWeb();
			out.setResponse(this.provinsiDao.save(jur)+"");
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
	public ResponseEntity<?> update(@RequestBody Provinsi kot) {
		try {
			this.provinsiDao.save(kot);
			return new ResponseEntity<>(kot, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//SEARCH
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Provinsi>> search(@PathVariable String name) {
		try {
			List<Provinsi> jurList = this.provinsiDao.search(name);
			System.out.println(name);
			return new ResponseEntity<>(jurList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	

}
