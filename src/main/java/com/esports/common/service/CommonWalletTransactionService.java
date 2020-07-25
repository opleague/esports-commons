package com.esports.common.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esports.bean.TransactionType;
import com.esports.entities.WalletEntity;
import com.esports.entities.WalletTransactionEntity;
import com.esports.repo.WalletRespository;
import com.esports.repo.WalletTransactionRepository;

@Service
public class CommonWalletTransactionService {

	@Autowired
	WalletTransactionRepository walletTransactionRepository;
	@Autowired WalletRespository walletRespository;
	public void addMoneytotheWallet(Long userId) {

	}
	
	@Transactional(rollbackOn = SQLException.class)
	private void updateWallet(WalletEntity walletEntity, WalletTransactionEntity walletTransactionEntity) {
		walletTransactionRepository.save(walletTransactionEntity);
		walletRespository.save(walletEntity);
	}

	public void addMoneytotheWallet(long userId, String source, double amount, String transactionId,WalletEntity walletEntity) {
		WalletTransactionEntity walletTransactionEntity = new WalletTransactionEntity();
		walletTransactionEntity.setUserId(userId);
		walletTransactionEntity.setSource(source);
		walletTransactionEntity.setAmount(amount);
		walletTransactionEntity.setTransactionType(TransactionType.DE.name());
		walletTransactionEntity.setTransactionId(transactionId);
		updateWallet(walletEntity,walletTransactionEntity);
	}
	
	public void addMoneytotheWallet(long userId, String source,String transactionType, double amount, String transactionId,WalletEntity walletEntity) {
		WalletTransactionEntity walletTransactionEntity = new WalletTransactionEntity();
		walletTransactionEntity.setUserId(userId);
		walletTransactionEntity.setSource(source);
		walletTransactionEntity.setAmount(amount);
		walletTransactionEntity.setTransactionType(transactionType);
		walletTransactionEntity.setTransactionId(transactionId);
		updateWallet(walletEntity,walletTransactionEntity);
	}
	
	@Transactional(rollbackOn = SQLException.class)
	public void saveAllToDB(List<WalletTransactionEntity> walletTransactionEntities,
			List<WalletEntity> walletEntities) {
		walletTransactionRepository.saveAll(walletTransactionEntities);
		walletRespository.saveAll(walletEntities);
		
	}
	
	public boolean withDrawMoneyFromWallet(Long userId, String source, double amount, String transactionId,
			WalletEntity walletEntity) {
		WalletTransactionEntity walletTransactionEntity = new WalletTransactionEntity();
		walletTransactionEntity.setUserId(userId);
		walletTransactionEntity.setSource(source);
		walletTransactionEntity.setAmount(amount);
		walletTransactionEntity.setTransactionType(TransactionType.WD.name());
		walletTransactionEntity.setTransactionId(transactionId);
		Double userAmount = walletEntity.getUseramount();
		userAmount = userAmount == null ? new Double(0) : userAmount;
		
		if(userAmount < amount) {
			return false;
		}
		double remAmount = userAmount - amount;
		walletEntity.setUseramount(remAmount);
		try{
			updateWallet(walletEntity,walletTransactionEntity);
			return true;
		}catch (Exception e) {
			//return false;
		}
		return false;
	}
}
