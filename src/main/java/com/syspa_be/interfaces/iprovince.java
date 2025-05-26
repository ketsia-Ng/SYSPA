package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.province;


public interface iprovince {
	Boolean save(province model);
	Page<province>collectionsprovinces(String search,Pageable pageable);
	Boolean changeretat(Long id);
	List<province>collectionallprovinces();
}
