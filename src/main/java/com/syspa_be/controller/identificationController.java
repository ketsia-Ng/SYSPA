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

import com.syspa_be.entity.identification;
import com.syspa_be.interfaces.iidentification;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/identification")
public class identificationController {
	@Autowired
	iidentification iidentification;
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody identification model) {
		if(iidentification.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	@GetMapping("/collectionsidentifications/{search}/{page}/{size}")
	public Page<identification>collectionsidentifications(@PathVariable("search")String search,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return iidentification.collectionsidentifications(search,PageRequest.of(page,size));
	}
	@GetMapping("/collectionsidentificationvalidation/{search}/{validation}/{page}/{size}")
	public Page<identification>collectionsidentificationvalidation(@PathVariable("search")String search,@PathVariable("validation")Integer validation,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return iidentification.collectionsidentificationvalidation(search,validation,PageRequest.of(page,size));
	}
	@GetMapping("/collectionsidentificationadminsection/{search}/{codeentite}/{page}/{size}")
	public Page<identification>collectionsidentificationadminsection(@PathVariable("search")String search,@PathVariable("codeentite")Long codeentite,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return iidentification.collectionsidentificationadminsection(search,codeentite,PageRequest.of(page,size));
	}
	@GetMapping("/collectionsidentificationconsultationavance/{search}/{codesection}/{page}/{size}")
	public Page<identification>collectionsidentificationconsultationavance(@PathVariable("search")String search,@PathVariable("codesection")Long codesection,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return iidentification.collectionsidentificationconsultationavance(search,codesection,PageRequest.of(page,size));
	}
	@GetMapping("/afficheridentifications/{identifiant}")
	public identification afficheridentifications(@PathVariable("identifiant")Long identifiant) {
		
		return iidentification.afficheridentifications(identifiant);
	}

}
