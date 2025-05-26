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
import com.syspa_be.interfaces.ifonction;
import com.syspa_be.repository.fonctionRepository;

@Service
public class fonctionService implements ifonction{
	@Autowired
	fonctionRepository fonctionRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public Boolean save(fonction model) {
		Boolean rep=false;
		Long id=model.getCodefonction()==null?0:model.getCodefonction();
		Optional<fonction>exist=fonctionRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setNomfonction(model.getNomfonction());
			exist.get().setCodesection(model.getCodesection());
			if(fonctionRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(fonctionRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
	}
	@Override
	public Page<fonction> collectionsfonctions(String search, Long codesection, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_fonction c ";
	    if (!search.equals("0")&&codesection==0) {
	    	nombre=nombre+" where c.nomfonction like'%"+search+"%'";
	    }    
	    if (search.equals("0")&&codesection!=0) {
	    	nombre=nombre+" where c.codesection ="+codesection;
	    }  
	    if (!search.equals("0")&&codesection!=0) {
	    	nombre=nombre+" where c.nomfonction like'%"+search+"%' and c.codesection="+codesection;
	    } 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT c.codefonction,c.nomfonction,c.codesection,c.etat,c.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY c.codefonction DESC) as line "
			+ "  FROM tab_fonction C";
    if (!search.equals("0")&&codesection==0) {
    	query=query+" where c.nomfonction like'%"+search+"%'";
    }    
    if (search.equals("0")&&codesection!=0) {
    	query=query+" where c.codesection ="+codesection;
    }  
    if (!search.equals("0")&&codesection!=0) {
    	query=query+" where c.nomfonction like'%"+search+"%' and c.codesection="+codesection;
    } 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<fonction> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(fonction.class));
return new PageImpl<fonction>(collections,pageable,count);
	}
	@Override
	public Boolean changeretat(Long id) {
		Boolean etat=false;
		fonction model=fonctionRepository.findById(id).orElseThrow(null);
		if(model!=null)
		etat=model.getEtat()==true?false:true;
		model.setEtat(etat);
		fonctionRepository.save(model);
		return etat;
	}
	@Override
	public List<fonction> collectionallfonctions(Long codesection) {
		// TODO Auto-generated method stub
		String query="SELECT f.codefonction,f.codesection,f.nomfonction,f.etat "
				+ " ,f.datecreation "
				+ "  FROM tab_fonction f "
				+ "where f.codesection="+codesection;
		List<fonction> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(fonction.class));
		return collections;
	}
}
