package com.esports.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esports.entities.MatchContestEntity;
import com.esports.entities.UserMatchContestEntity;

public interface UserMatchContestRepository extends JpaRepository<UserMatchContestEntity, Long>{

	List<UserMatchContestEntity> findByUserIdAndMatchContestIdAndActiveTrue(Long userId, long matchContestId);

	UserMatchContestEntity findRecordById(Long userMatchContestId);

	List<UserMatchContestEntity> findByMatchContestIdAndActiveTrue(Long matchContestId);

	List<UserMatchContestEntity> findByMatchContestIdAndStatusAndActiveTrue(Long matchContestId, String status);

	List<UserMatchContestEntity> findByMatchContestIdInAndStatus(Set<Long> matchContestIdsSet,
			String status);

	UserMatchContestEntity findRecordByIdAndUserId(Long userMatchContestId, Long userMatchContestId2);

	List<UserMatchContestEntity> findByUserIdAndStatusAndActiveTrue(Long userId, String name);

	List<UserMatchContestEntity> findByIdAndUserIdAndStatusAndActiveTrue(Long id, Long userId, String name);

	List<UserMatchContestEntity> findByMatchContestIdAndUserIdAndStatusAndActiveTrue(Long id, Long userId, String name);
	
	@Query(nativeQuery = true,
			value = "select userTeamId,userId,matchContestId  from user_match_contest "
					+ " where matchContestId in (:matchContestIds) and active = true and status = 'PAID'" )
	List<Object[]> findByMatchContestIdAndActiveTrue(@Param(value="matchContestIds")Set<Long> matchContestIds);

}
