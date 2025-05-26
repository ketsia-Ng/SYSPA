package com.syspa_be.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.syspa_be.entity.situation;
import com.syspa_be.models.identificationModel;

public interface isituation {
Boolean save(situation model);
ResponseEntity<?> affichersituation(Long id);
Page<identificationModel>collectionssituations(Long identidfiant,Integer validation,Pageable pageable);
Page<identificationModel>collectionssituationsection(Long identidfiant,Long codesection,Integer validation,Pageable pageable);
Page<identificationModel>collectionsadminsituationsection(Long identidfiant,Long codeentite,Integer validation,Pageable pageable);
void validationsituations(Long idsituation,Integer validation);
ResponseEntity<?> impressionlistidentifsection(Long codesection);
ResponseEntity<?> impressionlistidentifentite(Long codeentite);
ResponseEntity<?> impressionpersidentifentite(Long codeentite,Long identifiant);
ResponseEntity<?> impressionpersidentifsection(Long codesection,Long identifiant);
}
