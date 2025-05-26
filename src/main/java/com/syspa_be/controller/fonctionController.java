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

import com.syspa_be.entity.fonction;
import com.syspa_be.entity.fonction;
import com.syspa_be.interfaces.ifonction;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fonction")
public class fonctionController {
	@Autowired
	ifonction ifonction;
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody fonction model) {
		if(ifonction.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	@GetMapping("/collectionsfonctions/{search}/{codesection}/{page}/{size}")
	public Page<fonction>collectionsfonctions(@PathVariable("search")String search,@PathVariable("codesection")Long codesection,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		
		return ifonction.collectionsfonctions(search,codesection,PageRequest.of(page,size));
	}
	@GetMapping("/changerstatut/{id}")
	public Boolean changerstatut(@PathVariable ("id")Long id){
		return ifonction.changeretat(id);
	}
	@GetMapping("/collectionallfonctions/{codesection}")
	public List<fonction>collectionallfonctions(@PathVariable("codesection")Long codesection) {
		return ifonction.collectionallfonctions(codesection);
	}
}
