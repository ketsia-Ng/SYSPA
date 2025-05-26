package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.position;



public interface iposition {
	Boolean save(position model);
	Page<position>collectionsposition(String search,Pageable pageable);
	Boolean changeretat(Long id);
	List<position>collectionallposition();
	
	
}
