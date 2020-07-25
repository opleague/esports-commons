package com.esports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esports.entities.WithDrawEntity;

public interface WithdrwalRequestRepository extends JpaRepository<WithDrawEntity, Long>{

	WithDrawEntity findByUserIdAndStatusAndRequestTypeAndActiveTrue(Long userId, String requestType, String status);

	List<WithDrawEntity> findByStatusAndRequestTypeAndActiveTrueOrderByCreatedAtDesc(String status, String type);

	List<WithDrawEntity> findByUserIdAndStatusAndRequestTypeAndActiveTrueOrderByCreatedAtDesc(Long userId,
			String status, String type);

	WithDrawEntity findByIdAndUserIdAndStatusAndRequestTypeAndActiveTrue(Long id, Long userId, String status,
			String request);

}
