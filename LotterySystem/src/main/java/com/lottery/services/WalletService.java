package com.lottery.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lottery.system.beans.User;
import com.lottery.system.beans.Wallet;
import com.lottery.system.repository.WalletRepository;
import com.lottery.system.util.RandomGenerator;

@Service
public class WalletService extends BaseServices {
	
	@Autowired
	private WalletRepository walletRepository;
	
	public Long getBalance(String walletId) {
		if(!StringUtils.isEmpty(walletId)) {
			Wallet wallet=getById(walletId);
			if(wallet!=null) {
				return wallet.getAmount();
			}
		}
		return 0l;
	}
	
	public boolean isBalanceAvailable(String walletId,Long cost) {
		Long currentBal=getBalance(walletId);
		if(currentBal!=null) {
			if(currentBal>=cost) {
				return true;
			}
		}
		return false;
	}
	public boolean reduceAmount(Wallet wallet, Long cost) {
		if(wallet!=null && cost>0) {
			if(cost==0)
				return true;
			Long curBalance=wallet.getAmount();
			
			if(curBalance!=null && curBalance>=cost) {
				curBalance=curBalance-cost;
				wallet.setAmount(curBalance);
				saveOrUpdate(wallet);
				return true;
			}
		}
		return false;
	}
	public boolean reduceAmount(String walletId, Long cost) {
	
		if(!StringUtils.isEmpty(walletId) || cost>=0) {
			if(cost==0) {
				return true;
			}
			Wallet wallet=getById(walletId);
			return reduceAmount(wallet, cost);
		}
		else 
			return false;
	}
	
	public Wallet getById(String walletId) {
		Optional<Wallet> wallet=walletRepository.findById(walletId);
		return wallet.get();
	}
	
	public List<Wallet> findAll(){
		Iterable<Wallet> walltes= walletRepository.findAll();
		List<Wallet> lists=new ArrayList<>();
		Iterator<Wallet> itr=walltes.iterator();
		while(itr.hasNext()) {
			lists.add(itr.next());
		}
		return lists;
	}
	
	public void addMoney(Integer money,Wallet wallet) {
		if(money<=0 || wallet==null)
			return;
		Long totalAmount=wallet.getAmount();
		if(totalAmount!=null) {
			totalAmount+=money;
			wallet.setAmount(totalAmount);
			saveOrUpdate(wallet);
		}
	}
	public Wallet createDefaultWallet() {
		Wallet wallet=new Wallet();
		wallet.setName(getDefaultWalletName());
		wallet.setAmount(0l);
		wallet.setUser(new User());
		saveOrUpdate(wallet);
		return wallet;
	}
	
	public void saveOrUpdate(Wallet wallet) {
		walletRepository.save(wallet);
	}
	
	public String getDefaultWalletName() {
		RandomGenerator<String> generator=new RandomGenerator.RandomGeneratorBuilder<String>().build();
		String defaultName=generator.getRandomIdentifier();
		return defaultName;
	}
}
