package com.lottery.system.repository;

import java.util.Optional;

import com.lottery.system.beans.Wallet;

public class WalletRepository implements BaseRepository<Wallet, String> {

	@Override
	public <S extends Wallet> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Wallet> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Wallet> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Wallet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Wallet> findAllById(Iterable<String> ids) {
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
	public void delete(Wallet entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Wallet> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Wallet findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}}
