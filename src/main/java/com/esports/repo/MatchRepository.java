package com.esports.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.esports.entities.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity, Long>{

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update matches set active = false where id = ?1")
	int deleteEnityById(Long id);
	
	@Query("select m from matches m LEFT JOIN FETCH m.matchTeam mt LEFT JOIN FETCH m.matchcontest mc LEFT JOIN FETCH"
			+ " mt.team" )
	List<MatchEntity> findAllEntities();
	
	@Transactional(readOnly = true)
	@Query("select m from matches m LEFT JOIN FETCH m.matchTeam mt LEFT JOIN FETCH m.matchcontest mc LEFT JOIN FETCH"
			+ " mt.team where m.startTime > ?2 and m.tournamentId = ?1 and m.active = true" )	
	List<MatchEntity> findAllByTournamentIdAndStartTime(Long tournamentId, Date startTime, Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query("select m from matches m LEFT JOIN FETCH m.matchTeam mt LEFT JOIN FETCH m.matchcontest mc LEFT JOIN FETCH"
			+ " mt.team where m.id = ?1 and m.active = true" )	
	List<MatchEntity> findAllByMatchId(Long matchId, Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true,value = "select count(1) from matches group by tournamentId having tournamentId = ?1")
	int findTotalCountByTournamentId(Long tournamentId);

}
