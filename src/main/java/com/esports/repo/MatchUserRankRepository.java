package com.esports.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esports.entities.MatchUserRankEntity;

public interface MatchUserRankRepository extends JpaRepository<MatchUserRankEntity, Long>{

	
}
