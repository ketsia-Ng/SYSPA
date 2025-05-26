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

import com.syspa_be.entity.barem;
import com.syspa_be.interfaces.ibarem;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/barem")
public class baremController {
	@Autowired
	ibarem ibarem;
	
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody barem model) {
		if(ibarem.save(model)) {
			return ResponseEntity.ok().body(model);
		}else {
			return ResponseEntity.ok().body("enregistrement echouée...");
		}
	}
	@GetMapping("/collectionsbarems/{search}/{codefonction}/{page}/{size}")
	public Page<barem>collectionsbarems(@PathVariable("search")String search,@PathVariable("codefonction")Long codefonction ,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		System.out.println(search+" "+codefonction+" "+page+" "+size);
		return ibarem.collectionsbarems(search,codefonction,PageRequest.of(page,size));
	}
	@GetMapping("/changerstatut/{id}")
	public Boolean changerstatut(@PathVariable ("id")Long id){
		return ibarem.changeretat(id);
	}
	@GetMapping("/collectionsallbarems/{codefonction}")
	public List<barem> collectionsallbarems(@PathVariable ("codefonction")Long codefonction){
		return ibarem.collectionsallbarems(codefonction);
	}
}
