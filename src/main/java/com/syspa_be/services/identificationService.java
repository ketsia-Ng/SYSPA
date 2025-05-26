package com.syspa_be.services;

import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syspa_be.entity.identification;
import com.syspa_be.interfaces.iidentification;
import com.syspa_be.repository.identificationRepository;


@Service
public class identificationService implements iidentification {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	identificationRepository identificationRepository;
	@Override
	public Boolean save(identification model) {
		Boolean rep=false;
		Long id=model.getIdentifiant()==null?0:model.getIdentifiant();
		Optional<identification>exist=identificationRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setDateengagement(model.getDateengagement());
			exist.get().setDatenaiss(model.getDatenaiss());
			exist.get().setGenre(model.getGenre());
			exist.get().setLieunaiss(model.getLieunaiss());
			exist.get().setMatricule(model.getMatricule());
			exist.get().setNom(model.getNom());
			exist.get().setPostnom(model.getPostnom());
			exist.get().setPrenom(model.getPrenom());
			if(identificationRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(identificationRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
	}

	@Override
	public Page<identification> collectionsidentifications(String search, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_identification i ";
	    if (!search.equals("0")) {
	    	nombre=nombre+" where i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	} 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT  i.identifiant,i.nom,i.postnom,i.prenom "
			+ ",i.matricule,i.genre,i.datenaiss,i.dateengagement "
			+ ",i.lieunaiss,i.datecreation, "
			+ "ROW_NUMBER() OVER(ORDER BY i.identifiant DESC) as line "
			+ "  FROM tab_identification i";
    if (!search.equals("0")) {
    query=query+" where i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	
} 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identification> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identification.class));
return new PageImpl<identification>(collections,pageable,count);
	}



	@Override
	public identification afficheridentifications(Long identifiant) {
		
		return identificationRepository.findById(identifiant).get();
	}

	@Override
	public Page<identification> collectionsidentificationvalidation(String search, Integer validation,
			Pageable pageable) {
		String nombre="SELECT COUNT(*) "
				+ " from tab_identification i "
				+ " inner join tab_situation s on s.identifiant=i.identifiant "
				+ " where s.validation="+validation;
	    if (!search.equals("0")) {
	    	nombre=nombre+" and i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	} 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT  i.identifiant,i.nom,i.postnom,i.prenom "
			+ ",i.matricule,i.genre,i.datenaiss,i.dateengagement "
			+ ",i.lieunaiss,i.datecreation, "
			+ "ROW_NUMBER() OVER(ORDER BY i.identifiant DESC) as line "
			+ "  FROM tab_identification i "
			+ " inner join tab_situation s on s.identifiant=i.identifiant "
			+ " where s.validation="+validation;
    if (!search.equals("0")) {
    query=query+" and i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	
} 
    query=query+" group by i.identifiant,i.nom,i.postnom,i.prenom,i.matricule,i.genre,i.datenaiss,i.dateengagement,i.lieunaiss,i.datecreation";
    
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identification> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identification.class));
return new PageImpl<identification>(collections,pageable,count);
	}

	@Override
	public Page<identification> collectionsidentificationconsultationavance(String search, Long codesection,
			Pageable pageable) {
		String nombre="SELECT COUNT(*) "
				+ " from tab_identification i "
				+ " inner join tab_situation s on s.identifiant=i.identifiant ";		
	    if (!search.equals("0") && codesection==0) {
	    	nombre=nombre+" where i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	} else if (!search.equals("0") && codesection!=0) {
    	nombre=nombre+" where s.codesection="+codesection+" and i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	} else if (search.equals("0") && codesection!=0) {
    	nombre=nombre+" where  s.codesection="+codesection;
    	} 
	    
	 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT  i.identifiant,i.nom,i.postnom,i.prenom "
			+ ",i.matricule,i.genre,i.datenaiss,i.dateengagement "
			+ ",i.lieunaiss,i.datecreation, "
			+ "ROW_NUMBER() OVER(ORDER BY i.identifiant DESC) as line "
			+ "  FROM tab_identification i "
			+ " inner join tab_situation s on s.identifiant=i.identifiant ";
    if (!search.equals("0") && codesection==0) {
    	query=query+" where i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
} else if (!search.equals("0") && codesection!=0) {
	query=query+" where s.codesection="+codesection+" and i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
} else if (search.equals("0") && codesection!=0) {
	query=query+" where  s.codesection="+codesection;
	} 

    query=query+" group by i.identifiant,i.nom,i.postnom,i.prenom,i.matricule,i.genre,i.datenaiss,i.dateengagement,i.lieunaiss,i.datecreation";
    
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identification> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identification.class));
return new PageImpl<identification>(collections,pageable,count);
	}

	@Override
	public Page<identification> collectionsidentificationadminsection(String search,Long codeentite, Pageable pageable) {
		String nombre="SELECT COUNT(*) "
				+ " from tab_identification i "
				+ " inner join tab_situation s on s.identifiant=i.identifiant ";		
	    if (!search.equals("0") && codeentite==0) {
	    	nombre=nombre+" where i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	} else if (!search.equals("0") && codeentite!=0) {
		nombre=nombre+" where s.codeentite="+codeentite+" and i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
	} else if (search.equals("0") && codeentite!=0) {
		nombre=nombre+" where  s.codeentite="+codeentite;
		} 
	    
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT  i.identifiant,i.nom,i.postnom,i.prenom "
			+ ",i.matricule,i.genre,i.datenaiss,i.dateengagement "
			+ ",i.lieunaiss,i.datecreation, "
			+ "ROW_NUMBER() OVER(ORDER BY i.identifiant DESC) as line "
			+ "  FROM tab_identification i "
			+ " inner join tab_situation s on s.identifiant=i.identifiant ";
    if (!search.equals("0") && codeentite==0) {
    	query=query+" where i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
} else if (!search.equals("0") && codeentite!=0) {
	query=query+" where s.codeentite="+codeentite+" and i.nom like'%"+search+"%' or i.postnom like'%"+search+"%' or i.prenom like'%"+search+"%' or i.matricule like'%"+search+"%'";
} else if (search.equals("0") && codeentite!=0) {
	query=query+" where  s.codeentite="+codeentite;
	} 

    query=query+" group by i.identifiant,i.nom,i.postnom,i.prenom,i.matricule,i.genre,i.datenaiss,i.dateengagement,i.lieunaiss,i.datecreation";
    
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identification> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identification.class));
return new PageImpl<identification>(collections,pageable,count);
	}



}
