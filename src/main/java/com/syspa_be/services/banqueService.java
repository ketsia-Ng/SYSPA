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

import com.syspa_be.entity.banque;
import com.syspa_be.interfaces.ibanque;
import com.syspa_be.repository.banqueRepository;

@Service
public class banqueService implements ibanque{
@Autowired
banqueRepository banqueRepository;
@Autowired
JdbcTemplate jdbcTemplate;
	@Override
	public Boolean save(banque model) {
	Boolean rep=false;
	Long id=model.getCodebanque()==null?0:model.getCodebanque();
	Optional<banque>exist=banqueRepository.findById(id);
	if(exist.isPresent()) {
		exist.get().setNombanque(model.getNombanque());
		if(banqueRepository.save(exist.get())!=null) {
			rep=true;
		}
	}else {
		if(banqueRepository.save(model)!=null) {
			rep=true;
		}
	}
	return rep;
	}

	@Override
	public Page<banque> collectionsbanques(String search, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_banque c ";
	    if (!search.equals("0")) {
	    	nombre=nombre+" where c.nombanque like'%"+search+"%'";
	} 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT c.codebanque,c.nombanque,c.etat,c.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY c.codebanque DESC) as line "
			+ "  FROM tab_banque C";
    if (!search.equals("0")) {
    query=query+" where c.nombanque like'%"+search+"%' ";
} 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<banque> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(banque.class));
return new PageImpl<banque>(collections,pageable,count);
	}

	@Override
	public Boolean changeretat(Long id) {
		Boolean etat=false;
		banque model=banqueRepository.findById(id).orElseThrow(null);
		if(model!=null)
		etat=model.getEtat()==true?false:true;
		model.setEtat(etat);
		banqueRepository.save(model);
		return etat;
	}

	@Override
	public List<banque> collectionallbanques() {
		return banqueRepository.findAll();
	}

}
