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

import com.syspa_be.entity.banque;
import com.syspa_be.interfaces.ibanque;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/banque")
public class banqueController {
@Autowired
ibanque ibanque;
@PostMapping("/create")
public ResponseEntity<?> save(@RequestBody banque model) {
	if(ibanque.save(model)) {
		return ResponseEntity.ok().body(model);
	}else {
		return ResponseEntity.ok().body("enregistrement echouée...");
	}
}
@GetMapping("/collectionsbanques/{search}/{page}/{size}")
public Page<banque>collectionsbanques(@PathVariable("search")String search,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
	
	return ibanque.collectionsbanques(search,PageRequest.of(page,size));
}
@GetMapping("/changerstatut/{id}")
public Boolean changerstatut(@PathVariable ("id")Long id){
	return ibanque.changeretat(id);
}
@GetMapping("/collectionallbanques")
public List<banque> collectionallbanques(){
	return ibanque.collectionallbanques();
}
}
