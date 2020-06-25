package com.esports.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.esports.entities.MatchTeamEntity;

public interface MatchTeamRepository extends JpaRepository<MatchTeamEntity, Long>{

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update team_match set active = false where id = ?1")
	int deleteEnityById(Long id);
	
	@Query(nativeQuery = true,
			value = "select p.id,p.displayName, p.credits, p.imageUrl,tm.team_id,t.displayName as teamName from player p "
					+ " inner join team_players tp on tp.playerId = p.id "
					+ " inner join team_match tm on tm.team_id = tp.teamId "
					+ " inner join team t on t.id = tp.teamId where tm.match_id = ?1 "
					+ " and tm.active = true and p.active = true and tp.active = true and t.active = true")
	List<Object[]> findByMatchId(Long matchId);

}
