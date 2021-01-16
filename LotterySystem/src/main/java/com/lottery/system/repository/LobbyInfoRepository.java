package com.lottery.system.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lottery.system.beans.LobbyInfo;

@Repository
public class LobbyInfoRepository implements BaseRepository<LobbyInfo, Long>{

	@Override
	public <S extends LobbyInfo> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LobbyInfo> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<LobbyInfo> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<LobbyInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<LobbyInfo> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(LobbyInfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends LobbyInfo> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LobbyInfo findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LobbyInfo findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(LobbyInfo t) {
		// TODO Auto-generated method stub
		
	}

}
