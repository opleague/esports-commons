package com.esports.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esports.entities.RolesEntity;

public interface PlayerRolesRepository extends JpaRepository<RolesEntity, Long>{

}
