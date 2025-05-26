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

import com.syspa_be.entity.barem;
import com.syspa_be.interfaces.ibarem;
import com.syspa_be.repository.baremRepository;

@Service
public class baremService implements ibarem {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	baremRepository baremRepository;
	
	@Override
	public Boolean save(barem model) {
		Boolean rep=false;
		Long id=model.getCodebarem()==null?0:model.getCodebarem();
		Optional<barem>exist=baremRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setLibelle(model.getLibelle());
			exist.get().setCodefonction(model.getCodefonction());
			if(baremRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(baremRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
	}

	@Override
	public Page<barem> collectionsbarems(String search, Long codefonction, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_barem c ";
	    if (!search.equals("0")&&codefonction==0) {
	    	nombre=nombre+" where c.libelle like'%"+search+"%'";
	    }    
	    if (search.equals("0")&&codefonction!=0) {
	    	nombre=nombre+" where c.codefonction ="+codefonction;
	    }  
	    if (!search.equals("0")&&codefonction!=0) {
	    	nombre=nombre+" where c.libelle like'%"+search+"%' and c.codefonction="+codefonction;
	    } 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT c.codebarem,c.libelle,c.montant,c.codefonction,c.etat,c.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY c.codebarem DESC) as line "
			+ "  FROM tab_barem C";
    if (!search.equals("0")&&codefonction==0) {
    	query=query+" where c.libelle like'%"+search+"%'";
    }    
    if (search.equals("0")&&codefonction!=0) {
    	query=query+" where c.codefonction ="+codefonction;
    }  
    if (!search.equals("0")&&codefonction!=0) {
    	query=query+" where c.libelle like'%"+search+"%' and c.codefonction="+codefonction;
    } 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<barem> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(barem.class));
return new PageImpl<barem>(collections,pageable,count);
	}

	@Override
	public Boolean changeretat(Long id) {
		Boolean etat=false;
		barem model=baremRepository.findById(id).orElseThrow(null);
		if(model!=null)
		etat=model.getEtat()==true?false:true;
		model.setEtat(etat);
		baremRepository.save(model);
		return etat;
	}

	@Override
	public List<barem> collectionsallbarems(Long codefonction) {
		String query="SELECT c.codebarem,c.libelle,c.montant,c.codefonction,c.etat,c.datecreation  "
				+ " FROM tab_barem c "
				+ " where c.codefonction="+codefonction;
		List<barem> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(barem.class));
		return collections;
	}
}
