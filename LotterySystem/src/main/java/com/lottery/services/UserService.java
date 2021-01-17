package com.lottery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.system.beans.User;
import com.lottery.system.beans.Wallet;
import com.lottery.system.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WalletService walletService;
	
	public void createUser(User user) {
		Wallet defaultWallet=walletService.createDefaultWallet();
		user.setWallet(defaultWallet);
		saveOrUpdate(user);
		defaultWallet.setUser(user);
		walletService.saveOrUpdate(defaultWallet);
	}
	
	public void saveOrUpdate(User user) {
		userRepo.save(user);
	}
}
