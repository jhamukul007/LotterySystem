package com.lottery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.system.beans.LobbyInfo;
import com.lottery.system.repository.LobbyInfoRepository;

@Service
public class LobbyInfoService extends BaseServices {

	@Autowired
	private LobbyInfoRepository infoRepository;
	
	public void saveOrUpdate(LobbyInfo info) {
		infoRepository.save(info);
	}
}
