package com.syspa_be.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.syspa_be.entity.identification;

public interface iidentification {
	Boolean save(identification model);
	Page<identification>collectionsidentifications(String search,Pageable pageable);
	Page<identification>collectionsidentificationvalidation(String search,Integer validation,Pageable pageable);
	Page<identification>collectionsidentificationconsultationavance(String search,Long codesection,Pageable pageable);
	Page<identification>collectionsidentificationadminsection(String search,Long codeentite,Pageable pageable);
	identification afficheridentifications(Long identifiant);
}
