package com.esports.repo;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esports.entities.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>{

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update player set active = false where id = ?1")
	int deleteEnityById(Long id);
	
	@Query(nativeQuery = true, value = "select id,displayName,credits from player where id IN (:playerIds) and active = true")
	List<Object[]> findAllByIdAndActiveTrue(@Param(value = "playerIds") Set<Long> playerIds);

}
