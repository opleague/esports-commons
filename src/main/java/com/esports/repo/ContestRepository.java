package com.esports.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esports.entities.ContestEntity;

public interface ContestRepository extends JpaRepository<ContestEntity, Long>{

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update contest set active = false where id = ?1")
	int deleteEnityById(Long id);
	
	@Query(value = "select c.id,c.NumberOfWinners,c.entryFee,c.name,c.spots,c.winningAmount,cr.ranks, cr.prize , "
			+ " c.termsCondition from contest c left outer join contest_rank cr on cr.contestEntity_id = c.id  "
			+ " where c.id in(select contest_id from match_contest where match_id = 3) and c.active = true ", nativeQuery = true)
	List<Object[]> getContestByMatchId(@Param(value = "matchId")Long matchId);
	
	@Query(value = " select * from contest c where c.id in(select contest_id from match_contest where match_id =:matchId ) and c.active = true ", 
			nativeQuery = true)
	List<ContestEntity> getContestsByMatchId(@Param(value = "matchId")Long matchId);
	
	@Query(value = " select c.entryFee from contest c where id =:contestId  and c.active = true ", 
			nativeQuery = true)
	Object[] findContestById(@Param(value = "contestId")Long contestId);
}
