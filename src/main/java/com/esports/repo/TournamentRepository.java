package com.esports.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.esports.entities.TournamentEntity;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Long>{

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update tournament set active = false where id = ?1")
	int deleteEnityById(Long id);

	List<TournamentEntity> findAllByEsportId(Long esportId, Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true,value = "select * from tournament where esportId=:esportId and endTime is not null and endTime>:now and startTime < endTime and active= true order by startTime ")
	List<TournamentEntity> findAllByEsportIdAndStartTime(@Param(value ="esportId" )Long esportId, 
			@Param(value ="now" )Date now, Pageable pageable);
	
	@Query(nativeQuery = true,value = "select * from tournament where endTime is not null and endTime>:now and startTime < endTime and active= true order by startTime ")
	List<TournamentEntity> findAllByStartTime(
			@Param(value ="now" )Date now, Pageable pageable);

	@Transactional(readOnly = true)
	@Query(nativeQuery = true,value = "select t.* from tournament t where t.id = (select tournamentId from matches where id = ?1 and active= true)and t.active= true")
	TournamentEntity findRecordUsingMatchId(Long matchId);

}
