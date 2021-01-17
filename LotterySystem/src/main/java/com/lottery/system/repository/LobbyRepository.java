package com.lottery.system.repository;

import org.springframework.stereotype.Repository;

import com.lottery.system.beans.LotteryLobby;

@Repository
public interface LobbyRepository extends BaseRepository<LotteryLobby, String> {
	
}
