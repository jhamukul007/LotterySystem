package com.lottery.system.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends Serializable, ID extends Serializable> extends CrudRepository<T, ID> {
	
}
