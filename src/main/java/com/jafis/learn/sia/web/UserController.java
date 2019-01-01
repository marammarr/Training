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
import com.jafis.learn.sia.dao.UserDao;
import com.jafis.learn.sia.model.User;
import com.jafis.learn.sia.object.ResponseWeb;

@RestController
@RequestMapping(value="/api/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	//LOGIN
	@CrossOrigin
	@RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			System.out.println("USER : "+user.getUsername()+", PASS "+user.getPassword());
			User un = this.userDao.checkUsername(user.getUsername());
			ResponseWeb out = new ResponseWeb();
			if(un!=null) { // Kalo username ada
				User us = this.userDao.login(user.getUsername(), user.getPassword());
				if(us!=null) { // Kalau data username dan pass ada
					user = us;
					out.setResponse(user.getUserid());
					out.setDescription("Login Berhasil");
					out.setParameterNilai(1);
					return new ResponseEntity<>(out, HttpStatus.OK);
				}else {		// Kalau tidak ada un dan pass
					out.setResponse("Login Gagal");
					out.setParameterNilai(0);
					return new ResponseEntity<>(out, HttpStatus.OK);
				}
			}else { // Username tak ada
				out.setParameterNilai(-1);
				out.setResponse("Username tak ada");
				return new ResponseEntity<>(out, HttpStatus.OK);
			}
			//return new ResponseEntity<>(out, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}	
	
	// UPDATE
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			
			User usTersimpan = this.userDao.save(user);
			ResponseWeb out = new ResponseWeb();
			out.setResponse("Data Tersimpan");
			ObjectMapper toJson = new ObjectMapper();
			String json = toJson.writeValueAsString(usTersimpan);
			out.setDescription("Data diubah menjadi \n"+json);
			out.setParameterNilai(1);
			return new ResponseEntity<>(out, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	// DELETE
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	//public ResponseEntity<?> delete(@PathVariable String id) {
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			//this.userDao.delete(id);
			User u = this.userDao.checkUserId(id);
			ResponseWeb out = new ResponseWeb();
			if(u!=null) {
				u.setDeleted(true);
				this.userDao.save(u);
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

	// CARI USERNAME
		@CrossOrigin
		@RequestMapping(value="{un}", method=RequestMethod.GET)
		public ResponseEntity<?> cariUsername(@PathVariable String un) {
			try {
				User user = this.userDao.findOne(un);
				ResponseWeb out = new ResponseWeb();
				if(user!=null) {
					out.setResponse("Terdapat data "+un);
					out.setParameterNilai(1);
				}
				return new ResponseEntity<>(out,HttpStatus.OK);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
			}
		}
		
	// GET ALL DATA
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		try {
			List<User> user = this.userDao.findAll();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET ALL DATA YANG BELUN TERDELETE
	@CrossOrigin
	@RequestMapping(value="/nodeleted/", method=RequestMethod.GET)
	public ResponseEntity<List<User>> yangAda() {
		try {
			List<User> user = this.userDao.ambilDataYangAda();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// INSERT
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody User user) {
		try {
			user.setDeleted(false);
			this.userDao.save(user);
			ObjectMapper json = new ObjectMapper();
			ResponseWeb out = new ResponseWeb();
			out.setResponse("Data Tersimpan");
			out.setParameterNilai(1);
			out.setDescription(json.writeValueAsString(user));
			return new ResponseEntity<>(out, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}	

}

