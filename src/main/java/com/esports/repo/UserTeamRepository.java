package com.esports.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esports.entities.UserTeamEntity;

public interface UserTeamRepository extends JpaRepository<UserTeamEntity, Long>{
	
	@Query(nativeQuery = true,
			value = "select playerId from user_team_player where userTeamId "
					+ " in ( select userTeamId from user_match_contest where matchContestId = ?1 "
					+ " and status = 'PAID' and active = true and userId = ?2) and active = true")
	List<BigInteger> findUserTeam(Long matchContestId, Long userId);

}
