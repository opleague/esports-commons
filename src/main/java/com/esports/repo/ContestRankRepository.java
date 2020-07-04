package com.esports.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esports.entities.ContestRankEntity;

public interface ContestRankRepository extends JpaRepository<ContestRankEntity, Long>{
	
	@Query(nativeQuery = true,
			value = "select contestEntity_id,ranks,prize from contest_rank where "
					+ " contestEntity_id in (:contestIds) and active = true")
	List<Object[]> findAllByContestIdAndTrue(@Param(value = "contestIds")Set<Long> contestIds);

	

}
