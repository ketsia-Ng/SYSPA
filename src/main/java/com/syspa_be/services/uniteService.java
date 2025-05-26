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

import com.syspa_be.entity.fonction;
import com.syspa_be.entity.unite;
import com.syspa_be.entity.unite;
import com.syspa_be.interfaces.iunite;
import com.syspa_be.repository.uniteRepository;

@Service
public class uniteService implements iunite{
@Autowired
uniteRepository uniteRepository;
@Autowired
JdbcTemplate jdbcTemplate;
	@Override
	public Boolean save(unite model) {
		Boolean rep=false;
		Long id=model.getCodeunite()==null?0:model.getCodeunite();
		Optional<unite>exist=uniteRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setNomunite(model.getNomunite());
			exist.get().setCodesection(model.getCodesection());
			if(uniteRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(uniteRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
	}
	@Override
	public Page<unite> collectionsunites(String search, Long codesection, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_unite c ";
	    if (!search.equals("0")&&codesection==0) {
	    	nombre=nombre+" where c.nomunite like'%"+search+"%'";
	    }    
	    if (search.equals("0")&&codesection!=0) {
	    	nombre=nombre+" where c.codesection ="+codesection;
	    }  
	    if (!search.equals("0")&&codesection!=0) {
	    	nombre=nombre+" where c.nomunite like'%"+search+"%' and c.codesection="+codesection;
	    } 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT c.codeunite,c.nomunite,c.codesection,c.etat,c.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY c.codeunite DESC) as line "
			+ "  FROM tab_unite C";
    if (!search.equals("0")&&codesection==0) {
    	query=query+" where c.nomunite like'%"+search+"%'";
    }    
    if (search.equals("0")&&codesection!=0) {
    	query=query+" where c.codesection ="+codesection;
    }  
    if (!search.equals("0")&&codesection!=0) {
    	query=query+" where c.nomunite like'%"+search+"%' and c.codesection="+codesection;
    } 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<unite> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(unite.class));
return new PageImpl<unite>(collections,pageable,count);
	}
	@Override
	public Boolean changeretat(Long id) {
		Boolean etat=false;
		unite model=uniteRepository.findById(id).orElseThrow(null);
		if(model!=null)
		etat=model.getEtat()==true?false:true;
		model.setEtat(etat);
		uniteRepository.save(model);
		return etat;
	}
	@Override
	public List<unite> collectionallunite(Long codesection) {
		String query="SELECT u.codeunite,u.codesection,u.nomunite,u.etat "
				+ " ,u.datecreation "
				+ "  FROM tab_unite u "
				+ "where u.codesection="+codesection;
		List<unite> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(unite.class));
		return collections;
	}



}
