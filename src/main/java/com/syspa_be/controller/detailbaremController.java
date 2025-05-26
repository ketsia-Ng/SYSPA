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

import com.syspa_be.entity.detailbarem;
import com.syspa_be.interfaces.idetailbarem;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/detailbarem")
public class detailbaremController {
	
	@Autowired
	idetailbarem idetailbarem;
	
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody detailbarem model) {
		if(idetailbarem.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	
	@GetMapping("/collectionsdetailbarems/{search}/{codebarem}/{page}/{size}")
	public Page<detailbarem>collectionsdetailbarems(@PathVariable("search")String search,@PathVariable("codebarem")Long codebarem ,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return idetailbarem.collectionsdetailbarems(search,codebarem,PageRequest.of(page,size));
	}
	
	@GetMapping("/supprimerdetailbarem/{id}")
	public Boolean supprimerdetailbarem(@PathVariable ("id")Long id){
		return idetailbarem.supprimerdetailbarem(id);
	}
	
	@GetMapping("/changerstatut/{id}")
	public Boolean changerstatut(@PathVariable ("id")Long id){
		return idetailbarem.changeretat(id);
	}
}
