package com.esports.repo;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esports.entities.MatchContestEntity;

public interface MatchContestRepository extends JpaRepository<MatchContestEntity, Long> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update match_contest set active = false where id = ?1")
	int deleteEnityById(Long id);

	@Query(nativeQuery = true, value = "select id from match_contest where match_id =:matchId and contest_id=:contestId")
	long findByMatchIdAndContestId(@Param(value = "matchId") long matchId, @Param(value = "contestId") long contestId);

	@Query(nativeQuery = true, value = "select contest_id, match_id from match_contest where id= ?1 and active = true")
	List<Object[]> findRecordById(Long matchContestId);

	@Query(nativeQuery = true, value = "select contest_id, match_id,id from match_contest where id in (?1) and active = true")
	List<Object[]> findRecordsByIdIn(Set<Long> matchContestId);

	@Query(nativeQuery = true, value = "select id, contest_id from match_contest where match_id =?1 and contest_id in (?2) and active = true")
	List<Object[]> findByMatchIdAndContestIdIn(Long matchId, Set<Long> contestIdsSet);

	@Query(nativeQuery = true, value = "select umc.id from user_match_contest umc where "
			+ " umc.matchContestId  in (:matchcontestIdSet) "
			+ " and umc.status = 'PAID' and umc.active = true and userId = :userId")
	List<BigInteger> findAllByMatchId(@Param(value = "matchcontestIdSet") Set<Long> matchcontestIdSet, @Param(value ="userId")Long userId);
	
	@Query(nativeQuery = true, value = "select mc.id from match_contest mc where mc.contest_id in(:contestIdSet) and mc.active = true")
	List<BigInteger> findAllRecordsByContestIdIn(@Param(value = "contestIdSet") Set<Long> contestIdSet);
	
	
	@Query(nativeQuery = true, 
			value = "select id,contest_id from match_contest where match_id = :matchId and active = true")
	List<Object[]> findAllByMatchIdAndTrue(@Param(value = "matchId")Long matchId);

}
