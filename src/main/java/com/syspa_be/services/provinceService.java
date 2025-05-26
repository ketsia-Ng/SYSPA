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

import com.syspa_be.entity.province;
import com.syspa_be.interfaces.iprovince;
import com.syspa_be.repository.provinceRepository;
@Service
public class provinceService implements iprovince{
	@Autowired
	provinceRepository provinceRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
		@Override
		public Boolean save(province model) {
		Boolean rep=false;
		Long id=model.getCodeprovince()==null?0:model.getCodeprovince();
		Optional<province>exist=provinceRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setNomprovince(model.getNomprovince());
			if(provinceRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(provinceRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
		}

		@Override
		public Page<province> collectionsprovinces(String search, Pageable pageable) {
			String nombre="SELECT COUNT(*)"
					+ "from tab_province p ";
		    if (!search.equals("0")) {
		    	nombre=nombre+" where p.nomprovince like'%"+search+"%'";
		} 
			Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
	    String query="with QUERY AS("
				+"SELECT p.codeprovince,p.nomprovince,p.etat,p.datecreation,  "
				+ "ROW_NUMBER() OVER(ORDER BY p.codeprovince DESC) as line "
				+ "  FROM tab_province p";
	    if (!search.equals("0")) {
	    query=query+" where p.nomprovince like'%"+search+"%' ";
	} 
	query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

	List<province> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(province.class));
	return new PageImpl<province>(collections,pageable,count);
		}

		@Override
		public Boolean changeretat(Long id) {
			Boolean etat=false;
			province model=provinceRepository.findById(id).orElseThrow(null);
			if(model!=null)
			etat=model.getEtat()==true?false:true;
			model.setEtat(etat);
			provinceRepository.save(model);
			return etat;
		}

		@Override
		public List<province> collectionallprovinces() {
			// TODO Auto-generated method stub
			return provinceRepository.findAll();
		}

}
