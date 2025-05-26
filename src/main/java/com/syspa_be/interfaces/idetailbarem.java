package com.syspa_be.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.detailbarem;

public interface idetailbarem {
	Boolean save(detailbarem model);
	Page<detailbarem>collectionsdetailbarems(String search,Long codebarem,Pageable pageable);
	Boolean supprimerdetailbarem(Long id);
	Boolean changeretat(Long id);
}
