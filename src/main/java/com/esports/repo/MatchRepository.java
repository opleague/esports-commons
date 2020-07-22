package com.esports.repo;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
	
	@Query("select distinct(m) from matches m JOIN FETCH m.matchTeam mt JOIN FETCH m.matchcontest mc JOIN FETCH"
			+ " mt.team" )
	List<MatchEntity> findAllEntities();
	
	@Transactional(readOnly = true)
	@Query("select m from matches m LEFT JOIN FETCH m.matchTeam mt LEFT JOIN FETCH m.matchcontest mc LEFT JOIN FETCH"
			+ " mt.team where m.endTime > ?2 and m.startTime > ?2 and m.startTime < m.endTime and m.tournamentId = ?1 and m.active = true" )	
	List<MatchEntity> findAllByTournamentIdAndStartTime(Long tournamentId, Date startTime, Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query("select m from matches m LEFT JOIN FETCH m.matchTeam mt LEFT JOIN FETCH m.matchcontest mc LEFT JOIN FETCH"
			+ " mt.team where m.id = ?1 and m.active = true" )	
	List<MatchEntity> findAllByMatchId(Long matchId, Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true,value = "select count(1) from matches group by tournamentId having tournamentId = ?1")
	int findTotalCountByTournamentId(Long tournamentId);
	
	@Query(nativeQuery = true,value = "select id,tournamentId,matchNumber,credits,endTime,startTime,matchMap,name from matches where id IN (?1) and active = true")
	List<Object[]> findAllByIdIn(Set<Long> matchIds);
	
	@Query(nativeQuery = true,value = "select matchNumber,matchMap,startTime,name,endTime,credits from matches where id =?1 and active = true")
	List<Object[]> findMatchById(Long matchId);

}
