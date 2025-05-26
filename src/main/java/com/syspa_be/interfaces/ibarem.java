package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.barem;

public interface ibarem {
	Boolean save(barem model);
	Page<barem>collectionsbarems(String search,Long codefonction,Pageable pageable);
	Boolean changeretat(Long id);
	List<barem>collectionsallbarems(Long codefonction);
	
	
}
