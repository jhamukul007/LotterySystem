package com.lottery.system.repository;


import org.springframework.stereotype.Repository;

import com.lottery.system.beans.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{
	
}
