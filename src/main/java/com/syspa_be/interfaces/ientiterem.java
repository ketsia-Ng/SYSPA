package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.entiterem;
import com.syspa_be.models.identificationModel;

public interface ientiterem {
	Boolean save(entiterem model);
	Page<entiterem>collectionsentiterems(String search,Long codesection,Long codeprovince,Pageable pageable);
	Boolean changeretat(Long id);
	List<entiterem>collectionallentiterem(Long codesection,Long codeprovince);
	Page<identificationModel>collectionallentiteremunere(String search,Long codesection,Long codeprovince,Pageable pageable);
}
