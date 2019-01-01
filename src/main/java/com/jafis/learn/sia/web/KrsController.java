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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jafis.learn.sia.dao.KrsDao;
import com.jafis.learn.sia.model.Krs;
import com.jafis.learn.sia.object.ResponseWeb;

@RestController
@RequestMapping(value= {"/krs","/api/krs"})
public class KrsController {
	@Autowired
	private KrsDao krsDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//GET ID
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Krs> get(@PathVariable Long id){
		try {
			Krs jur = this.krsDao.findOne(id);
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
			Krs u = this.krsDao.checkKrsId(id);
			ResponseWeb out = new ResponseWeb();
			if(u!=null) {
				u.setDihapus(true);
				this.krsDao.save(u);
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
	public ResponseEntity<List<Krs>> getAll() {
		try {
			List<Krs> jur = this.krsDao.ambilDataYangAda();//this.krsDao.findAll();
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Krs jur) {
		try {
			jur.setDihapus(false);
			ResponseWeb out = new ResponseWeb();
			out.setResponse(this.krsDao.save(jur)+"");
			out.setParameterNilai(1);
			return new ResponseEntity<>(out, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}	
	
	//INSERT BANYAK
		@CrossOrigin
		@RequestMapping(value="/multi", method=RequestMethod.POST)
		public ResponseEntity<?> insertMulti(@RequestBody String json) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				ResponseWeb out = new ResponseWeb();
				JsonNode rootArray = mapper.readTree(json);
				for(JsonNode root : rootArray) {
					Krs krs = mapper.treeToValue(root, Krs.class);
					krs.setDihapus(false);
					out.setResponse(mapper.writeValueAsString((Krs)this.krsDao.save(krs)+""));
					out.setParameterNilai(1);
				}
				
				return new ResponseEntity<>(out, HttpStatus.CREATED);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
			}
		}	

	//UPDATE
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Krs kot) {
		try {
			this.krsDao.save(kot);
			return new ResponseEntity<>(kot, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//SEARCH
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Krs>> search(@PathVariable String name) {
		try {
			List<Krs> jurList = this.krsDao.search(name);
			System.out.println(name);
			return new ResponseEntity<>(jurList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	

}
