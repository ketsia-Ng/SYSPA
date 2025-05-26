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

import com.syspa_be.entity.unite;
import com.syspa_be.interfaces.iunite;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/unite")
public class uniteController {
	@Autowired
	iunite iunite;
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody unite model) {
		if(iunite.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	@GetMapping("/collectionsunites/{search}/{codesection}/{page}/{size}")
	public Page<unite>collectionsunites(@PathVariable("search")String search,@PathVariable("codesection")Long codesection,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return iunite.collectionsunites(search,codesection,PageRequest.of(page,size));
	}
	@GetMapping("/changerstatut/{id}")
	public Boolean changerstatut(@PathVariable ("id")Long id){
		return iunite.changeretat(id);
	}
	@GetMapping("/collectionallunite/{codesection}")
	public List<unite> collectionallunite(@PathVariable ("codesection")Long codesection){
		return iunite.collectionallunite(codesection);
	}
}
