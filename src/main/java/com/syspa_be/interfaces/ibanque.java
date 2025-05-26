package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syspa_be.entity.banque;

public interface ibanque {
Boolean save(banque model);
Page<banque>collectionsbanques(String search,Pageable pageable);
Boolean changeretat(Long id);
List<banque>collectionallbanques();
}
