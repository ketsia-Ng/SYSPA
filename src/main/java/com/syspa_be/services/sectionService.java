package com.syspa_be.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syspa_be.entity.section;
import com.syspa_be.interfaces.isection;
import com.syspa_be.repository.sectionRepository;

@Service
public class sectionService implements isection{
	@Autowired
	sectionRepository sectionRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
		@Override
		public Boolean save(section model) {
		Boolean rep=false;
		Long id=model.getCodesection()==null?0:model.getCodesection();
		Optional<section>exist=sectionRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setNomsection(model.getNomsection());
			if(sectionRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(sectionRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
		}

		@Override
		public Page<section> collectionssections(String search, Pageable pageable) {
			String nombre="SELECT COUNT(*)"
					+ "from tab_section p ";
		    if (!search.equals("0")) {
		    	nombre=nombre+" where p.nomsection like'%"+search+"%'";
		} 
			Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
	    String query="with QUERY AS("
				+"SELECT p.codesection,p.nomsection,p.etat,p.datecreation,  "
				+ "ROW_NUMBER() OVER(ORDER BY p.codesection DESC) as line "
				+ "  FROM tab_section p";
	    if (!search.equals("0")) {
	    query=query+" where p.nomsection like'%"+search+"%' ";
	} 
	query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

	List<section> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(section.class));
	return new PageImpl<section>(collections,pageable,count);
		}

		@Override
		public Boolean changeretat(Long id) {
			Boolean etat=false;
			section model=sectionRepository.findById(id).orElseThrow(null);
			if(model!=null)
			etat=model.getEtat()==true?false:true;
			model.setEtat(etat);
			sectionRepository.save(model);
			return etat;
		}

		@Override
		public List<section> collectionallsections() {
			// TODO Auto-generated method stub
			return sectionRepository.findAll();
		}
}
