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
import com.syspa_be.entity.position;
import com.syspa_be.interfaces.iposition;
import com.syspa_be.repository.positionRepository;

@Service
public class positionService implements iposition{
@Autowired
positionRepository positionRepository;
@Autowired
JdbcTemplate jdbcTemplate;
	@Override
	public Boolean save(position model) {
		Boolean rep=false;
		Long id=model.getCodeposition()==null?0:model.getCodeposition();
		Optional<position>exist=positionRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setNomposition(model.getNomposition());
			if(positionRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(positionRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
	}

	@Override
	public Page<position> collectionsposition(String search,Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_position p ";
	    if (!search.equals("0")) {
	    	nombre=nombre+" where p.nomposition like'%"+search+"%'";
	} 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT p.codeposition,p.nomposition,p.etat,p.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY p.codeposition DESC) as line "
			+ "  FROM tab_position p";
    if (!search.equals("0")) {
    query=query+" where p.nomposition like'%"+search+"%' ";
} 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<position> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(position.class));
return new PageImpl<position>(collections,pageable,count);
	}

	@Override
	public Boolean changeretat(Long id) {
	Boolean etat=false;
	position p=positionRepository.findById(id).get();
	if(p!=null)
	etat=p.getEtat()==true?false:true;
	p.setEtat(etat);
	positionRepository.save(p);
	return etat;
	}

	@Override
	public List<position> collectionallposition() {
		// TODO Auto-generated method stub
		return positionRepository.findAll();
	}

}
