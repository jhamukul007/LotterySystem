package com.lottery.system.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lottery.services.UserService;
import com.lottery.system.beans.User;
import com.lottery.system.constants.UserConstants;

@RestController
public class RestUserApi extends BaseApi {
	
	@Autowired
	private UserService userService;
	
	@PutMapping(value =UserConstants.CREATE_USER, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> createUser(@RequestBody User user){
		if(user==null) {
			LOG.warn("User can't be null");
			return new ResponseEntity<Object>("Unable to add User",HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			userService.createUser(user);
		}
		catch (Exception e) {
			LOG.error("Getting error while creating user ",e);
			return new ResponseEntity<Object>("Unable to create user",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>("Created Sucessfully",HttpStatus.OK);
	}
	@RequestMapping("/")
	public String test() {
		return "Working fine";
	}
	
}
