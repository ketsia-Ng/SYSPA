package com.syspa_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syspa_be.entity.position;

@Repository
public interface positionRepository extends JpaRepository<position,Long>{

}
