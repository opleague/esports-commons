package com.esports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esports.entities.MatchStatsEntity;

public interface MatchStatRepository extends JpaRepository<MatchStatsEntity, Long>{

	@Query(nativeQuery = true,
			value = "select sum(count),count(1),player_id from match_stats where active = true and "
					+ " match_id in(select id from matches where tournamentId = ?1 and active = true) and "
					+ " attack_type_id in (?2) group by player_id ")
	List<Object[]> findAllByMatchIdAndActiveTrue(Long tournamentId, int attackTypeId);

	@Query(nativeQuery = true,
			value = "select count,points,player_id,attack_type_id from match_stats where active = true and "
					+ " match_id in( ?1) ")
	List<Object[]> findAllByMatchId(Long matchId);
	
	@Query(nativeQuery = true,
			value = "select count,points,player_id,attack_type_id from match_stats where active = true and "
					+ " match_id in( select match_id from match_contest where id = ?1 and active = true) ")
	List<Object[]> findAllUserTeamsByMatchId(Long matchContestId);
	
	List<MatchStatsEntity> findByMatchIdAndActiveTrue(Long matchId);
	
}
