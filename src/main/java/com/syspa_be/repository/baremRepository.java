package com.syspa_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syspa_be.entity.barem;
@Repository
public interface baremRepository extends JpaRepository<barem, Long>{

}
