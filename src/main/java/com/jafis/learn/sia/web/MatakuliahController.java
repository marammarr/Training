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
import com.jafis.learn.sia.dao.MatakuliahDao;
import com.jafis.learn.sia.model.Matakuliah;
import com.jafis.learn.sia.object.ResponseWeb;

@RestController
@RequestMapping(value= {"/matakuliah","/api/matakuliah"})
public class MatakuliahController {
	@Autowired
	private MatakuliahDao matakuliahDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//GET ID
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Matakuliah> get(@PathVariable Long id){
		try {
			Matakuliah jur = this.matakuliahDao.findOne(id);
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
			Matakuliah u = this.matakuliahDao.checkMatakuliahId(id);
			ResponseWeb out = new ResponseWeb();
			if(u!=null) {
				u.setDihapus(true);
				this.matakuliahDao.save(u);
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
	public ResponseEntity<List<Matakuliah>> getAll() {
		try {
			List<Matakuliah> jur = this.matakuliahDao.ambilDataYangAda();//this.matakuliahDao.findAll();
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Matakuliah jur) {
		try {
			jur.setDihapus(false);
			ResponseWeb out = new ResponseWeb();
			out.setResponse(this.matakuliahDao.save(jur)+"");
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
					Matakuliah matakuliah = mapper.treeToValue(root, Matakuliah.class);
					matakuliah.setDihapus(false);
					out.setResponse(mapper.writeValueAsString(this.matakuliahDao.save(matakuliah)+""));
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
	public ResponseEntity<?> update(@RequestBody Matakuliah kot) {
		try {
			this.matakuliahDao.save(kot);
			return new ResponseEntity<>(kot, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//SEARCH
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Matakuliah>> search(@PathVariable String name) {
		try {
			List<Matakuliah> jurList = this.matakuliahDao.search(name);
			System.out.println(name);
			return new ResponseEntity<>(jurList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	

}
