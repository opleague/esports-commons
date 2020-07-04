package com.esports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esports.entities.WalletTransactionEntity;

public interface WalletTransactionRepository  extends JpaRepository<WalletTransactionEntity, Long>{

	List<WalletTransactionEntity> findByUserIdAndActiveTrue(Long userId);

	List<WalletTransactionEntity> findByUserIdAndActiveTrueOrderByCreatedAtDesc(Long userId);

}
