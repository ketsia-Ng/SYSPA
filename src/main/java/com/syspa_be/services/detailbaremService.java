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
import com.syspa_be.entity.detailbarem;
import com.syspa_be.interfaces.idetailbarem;
import com.syspa_be.repository.baremRepository;
import com.syspa_be.repository.detailbaremRepository;

@Service
public class detailbaremService implements idetailbarem {
	@Autowired
	JdbcTemplate jdbcTemplate;
@Autowired 
detailbaremRepository detailbaremRepository;
@Autowired 
baremRepository baremRepository;
	@Override
	public Boolean save(detailbarem model) {
		Boolean rep=false;
		Long id=model.getId()==null?0:model.getId();
		Optional<detailbarem>exist=detailbaremRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setIntitule(model.getIntitule());
			if(detailbaremRepository.save(exist.get())!=null) {
			rep=true;
			}
		} else {
			if(detailbaremRepository.save(model)!=null) {
				barem barem=baremRepository.findById(model.getCodebarem()).get();
				barem.setMontant(barem.getMontant()+ model.getMontant());
				if(baremRepository.save(barem)!=null) {
					rep=true; 
				}
			}
		}
		return rep;
	}

	@Override
	public Page<detailbarem> collectionsdetailbarems(String search, Long codebarem, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_detailbarem c ";
	    if (!search.equals("0")&&codebarem==0) {
	    	nombre=nombre+" where c.intitule like'%"+search+"%'";
	    }    
	    if (search.equals("0")&&codebarem!=0) {
	    	nombre=nombre+" where c.codebarem ="+codebarem;
	    }  
	    if (!search.equals("0")&&codebarem!=0) {
	    	nombre=nombre+" where c.intitule like'%"+search+"%' and c.codebarem="+codebarem;
	    } 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT c.id,c.intitule,c.montant,c.sigle,c.codebarem,c.etat,c.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY c.id DESC) as line "
			+ "  FROM tab_detailbarem C";
    if (!search.equals("0")&&codebarem==0) {
    	query=query+" where c.intitule like'%"+search+"%'";
    }    
    if (search.equals("0")&&codebarem!=0) {
    	query=query+" where c.codebarem ="+codebarem;
    }  
    if (!search.equals("0")&&codebarem!=0) {
    	query=query+" where c.intitule like'%"+search+"%' and c.codebarem="+codebarem;
    } 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<detailbarem> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(detailbarem.class));
return new PageImpl<detailbarem>(collections,pageable,count);
	}

	@Override
	public Boolean changeretat(Long id) {
		Boolean etat=false;
		detailbarem model=detailbaremRepository.findById(id).get();
		barem barem=baremRepository.findById(model.getCodebarem()).get();
		if(model!=null)
		etat=model.getEtat()==true?false:true;
		if(etat==true) {
			barem.setMontant(barem.getMontant()+ model.getMontant());
		}else {
			barem.setMontant(barem.getMontant()- model.getMontant());
		}
		model.setEtat(etat);
		baremRepository.save(barem);
		detailbaremRepository.save(model); 
		
		return etat;
	}
	
	@Override
	public Boolean supprimerdetailbarem(Long id) {
		Boolean rep=false;
		detailbarem model=detailbaremRepository.findById(id).get();
		if(model.getEtat()==true) {
		barem barem=baremRepository.findById(model.getCodebarem()).get();
		barem.setMontant(barem.getMontant()- model.getMontant());
		if(baremRepository.save(barem)!=null) {
		rep=true;
		}
		}
		detailbaremRepository.delete(model);
		return rep;
	}
}
