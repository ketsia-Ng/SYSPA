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

import com.syspa_be.entity.section;
import com.syspa_be.interfaces.isection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/section")
public class sectionController {
@Autowired
isection isection;
@PostMapping("/create")
public ResponseEntity<?> save(@RequestBody section model) {
	if(isection.save(model)) {
		return ResponseEntity.ok().body(model);
	}else {
		return ResponseEntity.ok().body("enregistrement echouée...");
	}
}
@GetMapping("/collectionssections/{search}/{page}/{size}")
public Page<section>collectionssections(@PathVariable("search")String search,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
	
	return isection.collectionssections(search,PageRequest.of(page,size));
}
@GetMapping("/collectionallsections")
public List<section>collectionallsections() {
	System.out.println( isection.collectionallsections());
	return isection.collectionallsections();
}
@GetMapping("/changerstatut/{id}")
public Boolean changerstatut(@PathVariable ("id")Long id){
	return isection.changeretat(id);
}
}
