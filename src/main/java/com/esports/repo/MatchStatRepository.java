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
	List<Object[]> findAllByMatchIdAndTrue(Long tournamentId, int attackTypeId);

	

}
