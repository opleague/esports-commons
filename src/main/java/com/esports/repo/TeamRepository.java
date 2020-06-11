package com.esports.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.esports.entities.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>{

	@Transactional(readOnly = true)
	@Modifying
	@Query(nativeQuery = true, value = "update team set active = false where id = ?1")
	int deleteEnityById(Long id);
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = "select t.id,t.esportId, t.name,t.imageUrl,e.name as esportName , t.description, "
			+ " t.displayName, t.shortName , t.active,"
			+ "  t.createdAt, t.updatedAt,t.createdBy," 
			+  " t.updatedBy from team t left outer join esport e on t.esportId =  e.id order by t.name asc")
	List<Object[]> findAllByOrderByName();
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, 
	value = " select p.id,tp.teamId,p.displayName,p.imageUrl,p.name,p.credits from team_players tp inner join player p on p.id = tp.playerId  "
			+ "where tp.teamId IN(?1) and tp.active =true and p.active =true")
	List<Object[]> findAllByTeamIdsIn(Set<Long> teamIdSet);

}
