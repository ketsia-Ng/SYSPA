package com.syspa_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.syspa_be.models.identificationModel;
import com.syspa_be.entity.situation;
import com.syspa_be.interfaces.isituation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/situation")
public class situationController {
	@Autowired
	isituation isituation;
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody situation model) {
		if(isituation.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	@GetMapping("/collectionssituationsection/{identifiant}/{codesection}/{validation}/{page}/{size}")
	public Page<identificationModel>collectionssituationsection(@PathVariable("identifiant")Long identifiant,@PathVariable("codesection")Long codesection,@PathVariable("validation")Integer validation,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return isituation.collectionssituationsection(identifiant,codesection,validation,PageRequest.of(page,size));
	}
	@GetMapping("/collectionsadminsituationsection/{identifiant}/{codeentite}/{validation}/{page}/{size}")
	public Page<identificationModel>collectionsadminsituationsection(@PathVariable("identifiant")Long identifiant,@PathVariable("codeentite")Long codeentite,@PathVariable("validation")Integer validation,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return isituation.collectionsadminsituationsection(identifiant,codeentite,validation,PageRequest.of(page,size));
	}
	@GetMapping("/collectionssituations/{identifiant}/{validation}/{page}/{size}")
	public Page<identificationModel>collectionssituations(@PathVariable("identifiant")Long identifiant,@PathVariable("validation")Integer validation,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return isituation.collectionssituations(identifiant,validation,PageRequest.of(page,size));
	}
	@GetMapping("/validationsituations/{idsituation}/{validation}")
	public void validationsituations(@PathVariable("idsituation")String idsituation[],@PathVariable("validation")Integer validation) {
		for(String item:idsituation) {
		 isituation.validationsituations(Long.parseLong(item), validation);
		}
	}
	   @GetMapping("/impressionlistidentifsection/{codesection}")
	    public ResponseEntity<?> impressionlistidentifsection(@PathVariable("codesection")  Long codesection){
	    	return isituation.impressionlistidentifsection(codesection);
	    }
	   
	   @GetMapping("/impressionlistidentifentite/{codeentite}")
	    public ResponseEntity<?> impressionlistidentifentite(@PathVariable("codeentite")  Long codeentite){
	    	return isituation.impressionlistidentifentite(codeentite);
	    }
	   @GetMapping("/impressionpersidentifsection/{codesection}/{identifiant}")
	    public ResponseEntity<?> impressionpersidentifsection(@PathVariable("codesection")  Long codesection,@PathVariable("identifiant")  Long identifiant){
	    	return isituation.impressionpersidentifsection(codesection, identifiant);
	    }
	   @GetMapping("/affichersituation/{id}")
	    public ResponseEntity<?> affichersituation(@PathVariable("id")  Long id){
	    	return isituation.affichersituation(id);
	    }
	   
	   @GetMapping("/impressionpersidentifentite/{codeentite}/{identifiant}")
	    public ResponseEntity<?> impressionpersidentifentite(@PathVariable("codeentite")  Long codeentite,@PathVariable("identifiant")  Long identifiant){
	    	return isituation.impressionpersidentifentite(codeentite,identifiant);
	    }
}
