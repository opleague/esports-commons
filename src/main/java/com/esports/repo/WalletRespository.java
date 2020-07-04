package com.esports.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esports.entities.WalletEntity;

public interface WalletRespository extends JpaRepository<WalletEntity, Long>{

	WalletEntity findByUserIdAndActiveTrue(long userId);

	List<WalletEntity> findAllByUserIdInAndActiveTrue(Set<Long> userIdSet);

}
