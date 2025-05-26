package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.fonction;
import com.syspa_be.entity.province;

public interface ifonction {
	Boolean save(fonction model);
	Page<fonction>collectionsfonctions(String search,Long codesection,Pageable pageable);
	Boolean changeretat(Long id);
	List<fonction>collectionallfonctions(Long codesection);
}
