package com.syspa_be.controller;

import java.util.List;

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

import com.syspa_be.entity.entiterem;
import com.syspa_be.interfaces.ientiterem;
import com.syspa_be.models.identificationModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/entiterem")
public class entiteremController {
	@Autowired
	ientiterem ientiterem;
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody entiterem model) {
		if(ientiterem.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	@GetMapping("/collectionsentiterems/{search}/{codesection}/{codeprovince}/{page}/{size}")
	public Page<entiterem>collectionsentiterems(@PathVariable("search")String search,@PathVariable("codesection")Long codesection,@PathVariable("codeprovince")Long codeprovince,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		System.out.println(codesection+" "+codeprovince);
		return ientiterem.collectionsentiterems(search,codesection,codeprovince,PageRequest.of(page,size));
	}
	@GetMapping("/changerstatut/{id}")
	public Boolean changerstatut(@PathVariable ("id")Long id){
		return ientiterem.changeretat(id);
	}
	@GetMapping("/collectionallentiterem/{codesection}/{codeprovince}")
	public List<entiterem>collectionallentiterem(@PathVariable("codesection")Long codesection,@PathVariable("codeprovince")Long codeprovince) {
		
		return ientiterem.collectionallentiterem(codesection,codeprovince);
	}
	@GetMapping("/collectionallentiteremunere/{search}/{codesection}/{codeprovince}/{page}/{size}")
	public Page<identificationModel>collectionallentiteremunere(@PathVariable("search")String search,@PathVariable("codesection")Long codesection,@PathVariable("codeprovince")Long codeprovince,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return ientiterem.collectionallentiteremunere(search,codesection,codeprovince,PageRequest.of(page,size));
	}
}
