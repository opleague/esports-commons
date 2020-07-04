package com.esports.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esports.entities.UserTeamEntity;
import com.esports.entities.UserTeamPlayersEntity;

public interface UserTeamPlayerRepository extends JpaRepository<UserTeamPlayersEntity, Long>{

	List<UserTeamPlayersEntity> findAllByUserTeamIdIn(Set<Long> userTeamIds);

	List<UserTeamPlayersEntity> findAllByUserTeamIdAndActiveTrue(Long userTeamId);
	
	@Query(nativeQuery = true,
			value = "select userTeamId,playerId,leader from user_team_player where "
					+ " userTeamId in(:userTeamIdSet)  and active = true")
	List<Object[]> findAllByUserTeamIdInAndActiveTrue(@Param(value = "userTeamIdSet")Set<Long> userTeamIdSet);

}
