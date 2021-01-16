package com.lottery.system.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lottery.system.beans.LotteryLobby;

@Repository
public class LobbyRepository implements BaseRepository<LotteryLobby, String> {

	@Override
	public <S extends LotteryLobby> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LotteryLobby> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<LotteryLobby> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<LotteryLobby> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<LotteryLobby> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(LotteryLobby entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends LotteryLobby> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LotteryLobby findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}}
