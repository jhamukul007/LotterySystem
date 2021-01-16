package com.lottery.services;

import java.util.Date;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lottery.system.beans.LobbyInfo;
import com.lottery.system.beans.LotteryLobby;
import com.lottery.system.beans.User;
import com.lottery.system.beans.Wallet;
import com.lottery.system.repository.LobbyRepository;
import com.lottery.system.util.LobbyUtil;
import com.lottery.system.util.RandomGenerator;

@Service
public class LobbyService extends BaseServices {

	@Autowired
	private LobbyRepository lobbyRepository;

	@Autowired
	private LobbyInfoService infoService;

	@Autowired
	private WalletService walletService;

	public boolean createNewLobby(String name){
		try {
			LotteryLobby lobby=new LotteryLobby();
			lobby.setName(name);
			lobby.setUsers(new TreeSet<User>());
			LobbyInfo info=new LobbyInfo();
			info.setMaxSize(Integer.parseInt(LobbyUtil.getIns().getMaxSize()));
			info.setPrice(Integer.parseInt(LobbyUtil.getIns().getPrice()));
			info.setTotalPerson(0);
			info.setWinner(null);
			infoService.saveOrUpdate(info);
			lobby.setInfo(info);
			lobby.setActive(true);
			lobbyRepository.save(lobby);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean isLobbyFull(LotteryLobby lobby) {
		boolean isLobbyFull=true;
		if(lobby==null)
			return true;
		LobbyInfo info=lobby.getInfo();
		if(info==null) {
			return isLobbyFull;
		}
		if(info.getTotalPerson()!=null && info.getTotalPerson()<info.getMaxSize()) {
			return false;
		}
		return true;
	}
	public boolean isLobbyAllow(LotteryLobby lobby) {
		return !isLobbyFull(lobby);
	}

	public void addUserToLobby(User user,String lobbyId) throws RuntimeException{
		if(user!=null && !StringUtils.isEmpty(lobbyId)) {
			try {
				LotteryLobby lobby=findById(lobbyId);
				LobbyInfo info=lobby.getInfo();

				if(lobby!=null && info!=null) {
					if(info.getEndTime()!=null && info.getEndTime().getTime()>new Date().getTime()) {
						addUserToLobby(user, lobby);
					}
				}
			}
			catch (Exception e) {
				throw new RuntimeException(e.toString());
			}
		}
	}

	public void addUserToLobby(User user,LotteryLobby lobby) throws RuntimeException {
		boolean status=false;

		if(lobby !=null && user!=null) {
			if(isLobbyAllow(lobby)) {
				LobbyInfo info=lobby.getInfo();
				if(info!=null) {
					Integer totalPerson=info.getTotalPerson();
					if(totalPerson>=info.getMaxSize()) {
						System.out.println("Lobby Full Create new Lobby and this user in it");
						return;
					}
					info.setTotalPerson(totalPerson+1);
					infoService.saveOrUpdate(info);
				}
				SortedSet<User> users=lobby.getUsers();
				if(users!=null) {
					users.add(user);
				}
				try {
					Wallet wallet=user.getWallet();
					if(wallet!=null) {
						status=walletService.reduceAmount(wallet, Long.valueOf(info.getPrice()));
					}
				}
				catch (Exception e) {

				}
				if(!status) {
					users.remove(user);
				}
				lobbyRepository.save(lobby);
			}
			else {
				throw new RuntimeException("Lobby full now, Please join Another Lobby");
			}
		}
		else
			throw new IllegalArgumentException("user or lobby mustn't be null");
	}

	public User getWinner(String lobbyId) {
		if(StringUtils.isEmpty(lobbyId))
			return null;
		LotteryLobby lobby=findById(lobbyId);
		return getWinner(lobby);
	}

	public User getWinner(LotteryLobby lobby) {
		if(lobby!=null) {
			SortedSet<User> users=lobby.getUsers();
			if(users!=null && !users.isEmpty()) {
				try {
					RandomGenerator<User> randomGenerator=new RandomGenerator.RandomGeneratorBuilder().setData(users).build();
					User winner=randomGenerator.getRandom();
					if(winner!=null) {
						System.out.println("Winner as "+winner.toString());
						markLoddyEnded(lobby,winner);
						Integer prizeMoney=getPrizeMoney(lobby);
						if(prizeMoney!=0) {
							walletService.addMoney(prizeMoney, winner.getWallet());
							System.out.println(String.format("prize money %s added to % user wallet %s ",prizeMoney
									,winner.getId(),winner.getWallet().getId()));
							return winner;
						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public void markLoddyEnded(LotteryLobby lobby,User winner) {
		if(lobby!=null) {
			LobbyInfo info =lobby.getInfo();
			if(info!=null) {
				info.setEndTime(new Date());
				info.setWinner(winner);
				infoService.saveOrUpdate(info);
			}
			lobby.setActive(false);
			lobby.setInfo(info);
			saveOrUpdate(lobby);
		}
	}

	@SuppressWarnings("rawtypes")
	public String getLobbyNewName() {
		RandomGenerator<String> generator=new RandomGenerator.RandomGeneratorBuilder().build();
		String lobbyName=generator.getRandomIdentifier();
		System.out.println("Auto generated Lobby Name "+lobbyName);
		return lobbyName;
	}

	public void startLobby(String lobbyId) {
		if(StringUtils.isEmpty(lobbyId))
			throw new RuntimeException("lobbyId mustn't be null");
		LotteryLobby lobby=findById(lobbyId);
		startLottery(lobby);
	}

	public void startLottery(LotteryLobby lobby) {
		if(lobby!=null) {
			//Min person To Start Lottery
			Integer minPerson=Integer.parseInt(LobbyUtil.getIns().getMinPeron());
			if(minPerson!=null) {
				LobbyInfo info=lobby.getInfo();
				if(info!=null && info.getStartTime()!=null) {
					System.out.println("Lottery already started at "+info.getStartTime());
					throw new RuntimeException("Already Started");
				}
				Integer totalPersion=info.getTotalPerson();
				if(totalPersion!=null && totalPersion>=minPerson ) {
					System.out.println("Starting lottery on lobby id "+lobby.getId()+ " on time "+new Date());
					info.setStartTime(new Date());
					infoService.saveOrUpdate(info);
				}
			}
		}
	}

	public LotteryLobby findById(String lobbyId) {
		Optional<LotteryLobby> lobby=lobbyRepository.findById(lobbyId);
		return lobby.get();
	}

	public void saveOrUpdate(LotteryLobby lobby) {
		lobbyRepository.save(lobby);
	}

	public Integer getPrizeMoney(LotteryLobby lobby) {
		int prizeMoney=0;
		if(lobby!=null) {
			LobbyInfo info=lobby.getInfo();
			if(!lobby.isActive()) {
				if(info!=null && info.getEndTime().getTime()<new Date().getTime()) {
					Integer totalPersonInteger=info.getTotalPerson();
					Integer fixCost=info.getPrice();
					Integer totalCollectedMoney=(totalPersonInteger*fixCost);
					System.out.println("Total Collected Money form lobby "+lobby.getId() + " is "+totalCollectedMoney);
					prizeMoney=getPrizeMoney(totalCollectedMoney);
					if(prizeMoney==0) {
						throw new RuntimeException("Unable to calculte prize money");
					}
				}
			}
		}
		return prizeMoney;
	}
	private Integer getPrizeMoney(Integer collectedMoney) {
		int prizeMoney=0;
		try {
			String prizeMoneyPer=LobbyUtil.getIns().getPrizeMoneyPercentage();
			int per=Integer.parseInt(prizeMoneyPer);
			prizeMoney= collectedMoney-(collectedMoney*per)/100;
			System.out.println("Prize money "+prizeMoney);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return prizeMoney;
	}
}
