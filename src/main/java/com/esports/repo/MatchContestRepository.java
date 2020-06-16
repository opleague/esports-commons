package com.esports.repo;

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

}
