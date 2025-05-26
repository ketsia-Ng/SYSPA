package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.unite;

public interface iunite {
	Boolean save(unite model);
	Page<unite>collectionsunites(String search,Long codesection,Pageable pageable);
	Boolean changeretat(Long id);
	List<unite>collectionallunite(Long codesection);
}
