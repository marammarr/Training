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

import com.jafis.learn.sia.dao.MenuDao;
import com.jafis.learn.sia.model.Menu;
import com.jafis.learn.sia.object.ResponseWeb;

@RestController
@RequestMapping(value= {"/menu","/api/menu"})
public class MenuController {
	@Autowired
	private MenuDao menuDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//GET ID
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Menu> get(@PathVariable Long id){
		try {
			Menu jur = this.menuDao.findOne(id);
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
			Menu u = this.menuDao.checkMenuId(id);
			ResponseWeb out = new ResponseWeb();
			if(u!=null) {
				u.setDihapus(true);
				this.menuDao.save(u);
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
	public ResponseEntity<List<Menu>> getAll() {
		try {
			List<Menu> jur = this.menuDao.ambilDataYangAda();//this.menuDao.findAll();
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Menu jur) {
		try {
			jur.setDihapus(false);
			ResponseWeb out = new ResponseWeb();
			out.setResponse(this.menuDao.save(jur)+"");
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
	public ResponseEntity<?> update(@RequestBody Menu kot) {
		try {
			this.menuDao.save(kot);
			return new ResponseEntity<>(kot, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	//SEARCH
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Menu>> search(@PathVariable String name) {
		try {
			List<Menu> jurList = this.menuDao.search(name);
			System.out.println(name);
			return new ResponseEntity<>(jurList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	

}
