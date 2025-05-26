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

import com.syspa_be.entity.province;
import com.syspa_be.entity.province;
import com.syspa_be.interfaces.iprovince;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/province")
public class provinceController {
@Autowired
iprovince iprovince;
@PostMapping("/create")
public ResponseEntity<?> save(@RequestBody province model) {
	if(iprovince.save(model)) {
		return ResponseEntity.ok().body(model);
	}else {
		return ResponseEntity.ok().body("enregistrement echouée...");
	}
}
@GetMapping("/collectionsprovinces/{search}/{page}/{size}")
public Page<province>collectionsprovinces(@PathVariable("search")String search,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
	
	return iprovince.collectionsprovinces(search,PageRequest.of(page,size));
}
@GetMapping("/collectionallprovinces")
public List<province>collectionallprovinces() {
	
	return iprovince.collectionallprovinces();
}
@GetMapping("/changerstatut/{id}")
public Boolean changerstatut(@PathVariable ("id")Long id){
	return iprovince.changeretat(id);
}
}

