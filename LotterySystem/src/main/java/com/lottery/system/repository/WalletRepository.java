package com.lottery.system.repository;

import org.springframework.stereotype.Repository;

import com.lottery.system.beans.Wallet;

@Repository
public interface WalletRepository extends BaseRepository<Wallet, String> {
}
