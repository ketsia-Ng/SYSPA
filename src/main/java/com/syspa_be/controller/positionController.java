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

import com.syspa_be.entity.position;
import com.syspa_be.interfaces.iposition;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/position")
public class positionController {
	@Autowired
	iposition iposition;
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody position model) {
		if(iposition.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	@GetMapping("/collectionsposition/{search}/{page}/{size}")
	public Page<position>collectionsposition(@PathVariable("search")String search,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		return iposition.collectionsposition(search,PageRequest.of(page,size));
	}
	@GetMapping("/changeretat/{id}")
	public Boolean changeretat(@PathVariable("id")Long id) {
		return iposition.changeretat(id);
	}
	@GetMapping("/collectionallposition")
	public List<position> collectionallposition() {
		return iposition.collectionallposition();
	}
}
