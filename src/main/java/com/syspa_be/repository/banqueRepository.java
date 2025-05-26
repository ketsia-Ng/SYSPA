package com.syspa_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syspa_be.entity.banque;

@Repository
public interface banqueRepository extends JpaRepository<banque,Long> {

}
