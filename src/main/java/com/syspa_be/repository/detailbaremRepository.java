package com.syspa_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syspa_be.entity.detailbarem;

@Repository
public interface detailbaremRepository extends JpaRepository<detailbarem, Long>{

}
