package com.lottery.system.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lottery.services.LobbyService;
import com.lottery.system.beans.User;
import com.lottery.system.constants.LottaryLoddyApiConstants;

@RequestMapping(value = LottaryLoddyApiConstants.LOBBY)
@RestController
public class LoddyApi extends BaseApi {
	
	@Autowired
	private LobbyService lobbyService;
	
	@PutMapping(value = "/create/new")
	public ResponseEntity<?> createNewLobby(){
		String lobbyName=lobbyService.getLobbyNewName();
		boolean isCreated=lobbyService.createNewLobby(lobbyName);
		if(isCreated) {
			return new ResponseEntity<String>("Created Sucessfully",HttpStatus.ACCEPTED);
		}
		else
			return new ResponseEntity<String>("Unable to create new lobby",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping(value = "/winner/{lobbyId}")
	public ResponseEntity<User> getWinner(@PathVariable("lobbyId") String lobbyId){
		if(StringUtils.isEmpty(lobbyId)) {
			return new ResponseEntity<User>(new User(),HttpStatus.BAD_REQUEST);
		}
		User winner=lobbyService.getWinner(lobbyId);
		if(winner!=null) {
			return new ResponseEntity<User>(winner,HttpStatus.OK);
		}
		else
			return new ResponseEntity<User>(new User(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/lobby/start/{lobbyId}")
	public ResponseEntity<Object> startLobby(@PathVariable("lobbyId") String lobby){
		try {
			lobbyService.startLobby(lobby);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Unable to start Lottery! Please wait for some Time",
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>("Lottery Stated Sucessfully",HttpStatus.OK);
	}
	
	
}
