package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.section;

public interface isection {
	Boolean save(section model);
	Page<section>collectionssections(String search,Pageable pageable);
	Boolean changeretat(Long id);
	List<section>collectionallsections();
}
