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

import com.syspa_be.entity.entiterem;
import com.syspa_be.entity.fonction;
import com.syspa_be.interfaces.ientiterem;
import com.syspa_be.models.identificationModel;
import com.syspa_be.repository.entiteremRepository;

@Service
public class entiteremService implements ientiterem{
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	entiteremRepository entiteremRepository;

	@Override
	public Boolean save(entiterem model) {
		Boolean rep=false;
		Long id=model.getCodeentite()==null?0:model.getCodeentite();
		Optional<entiterem>exist=entiteremRepository.findById(id);
		if(exist.isPresent()) {
			exist.get().setNomentite(model.getNomentite());
			exist.get().setCodesection(model.getCodesection());
			exist.get().setCodeprovince(model.getCodeprovince());
			if(entiteremRepository.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(entiteremRepository.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
	}

	@Override
	public Page<entiterem> collectionsentiterems(String search, Long codesection, Long codeprovince, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+ "from tab_entiterem c ";
	    if (!search.equals("0")&& codesection==0 && codeprovince==0) {
	    	nombre=nombre+" where c.nomentite like'%"+search+"%'";
	    }    
	    if (search.equals("0")&& codesection!=0 && codeprovince==0) {
	    	nombre=nombre+" where c.codesection ="+codesection;
	    } 
	    if (search.equals("0")&& codesection==0 && codeprovince!=0) {
	    	nombre=nombre+" where c.codeprovince ="+codeprovince;
	    }  
	    
	    if (!search.equals("0")&& codesection!=0 && codeprovince==0) {
	    	nombre=nombre+" where c.nomentite like'%"+search+"%' and c.codesection="+codesection;
	    } if (!search.equals("0")&& codesection==0 && codeprovince!=0) {
	    	nombre=nombre+" where c.nomentite like'%"+search+"%' and c.codeprovince="+codeprovince;
	    } 
	    
	    if (search.equals("0")&& codesection!=0 && codeprovince!=0) {
	    	nombre=nombre+" where c.codeprovince="+codeprovince+" and c.codesection="+codesection;
	    } 
	    if (!search.equals("0")&&codesection!=0 && codeprovince!=0) {
	    	nombre=nombre+" where c.nomentite like'%"+search+"%' and c.codesection="+codesection+" and c.codeprovince="+codeprovince;
	    } 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"SELECT c.codeentite,c.nomentite,c.codesection,c.codeprovince,c.etat,c.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY c.codeentite DESC) as line "
			+ "  FROM tab_entiterem c";
    if (!search.equals("0")&&codesection==0 && codeprovince==0) {
    	query=query+" where c.nomentite like'%"+search+"%'";
    }    
    if (search.equals("0")&&codesection!=0 && codeprovince==0) {
    	query=query+" where c.codesection ="+codesection;
    } 
    if (search.equals("0")&&codesection==0 && codeprovince!=0) {
    	query=query+" where c.codeprovince ="+codeprovince;
    }  
    if (!search.equals("0")&&codesection!=0 && codeprovince==0) {
    	query=query+" where c.nomentite like'%"+search+"%' and c.codesection="+codesection;
    } if (!search.equals("0")&&codesection==0 && codeprovince!=0) {
    	query=query+" where c.nomentite like'%"+search+"%' and c.codeprovince="+codeprovince;
    } if (search.equals("0")&&codesection!=0 && codeprovince!=0) {
    	query=query+" where c.codeprovince="+codeprovince+" and c.codesection="+codesection;
    } 
    if (!search.equals("0")&&codesection!=0 && codeprovince!=0) {
    	query=query+" where c.nomentite like'%"+search+"%' and c.codesection="+codesection+" and c.codeprovince="+codeprovince;
    } 
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();
System.out.println(query);
List<entiterem> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(entiterem.class));
return new PageImpl<entiterem>(collections,pageable,count);
	}

	@Override
	public Boolean changeretat(Long id) {
		Boolean etat=false;
		entiterem model=entiteremRepository.findById(id).orElseThrow(null);
		if(model!=null)
		etat=model.getEtat()==true?false:true;
		model.setEtat(etat);
		entiteremRepository.save(model);
		return etat;
	}

	@Override
	public List<entiterem> collectionallentiterem(Long codesection, Long codeprovince) {
		String query="SELECT e.codeentite,e.codesection,e.codeprovince,e.nomentite,e.etat "
				+ " ,e.datecreation "
				+ "  FROM tab_entiterem e "
				+ "where e.codesection="+codesection+" and e.codeprovince="+codeprovince;
		List<entiterem> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(entiterem.class));
		return collections;
	}

	@Override
	public Page<identificationModel> collectionallentiteremunere(String search,Long codesection,Long codeprovince,Pageable pageable) {
	
		String nombre="SELECT COUNT(DISTINCT e.codeentite) "
				+ " from tab_entiterem e "
				+ "	inner join tab_situation s on s.codeentite=e.codeentite ";
	    if (!search.equals("0")&& codesection==0 && codeprovince==0) {
	    	nombre=nombre+" where e.nomentite like'%"+search+"%'";
	    }    
	    if (search.equals("0")&& codesection!=0 && codeprovince==0) {
	    	nombre=nombre+" where e.codesection ="+codesection;
	    } 
	    if (search.equals("0")&& codesection==0 && codeprovince!=0) {
	    	nombre=nombre+" where e.codeprovince ="+codeprovince;
	    }  
	    
	    if (!search.equals("0")&& codesection!=0 && codeprovince==0) {
	    	nombre=nombre+" where e.nomentite like'%"+search+"%' and e.codesection="+codesection;
	    } if (!search.equals("0")&& codesection==0 && codeprovince!=0) {
	    	nombre=nombre+" where e.nomentite like'%"+search+"%' and e.codeprovince="+codeprovince;
	    } 
	    
	    if (search.equals("0")&& codesection!=0 && codeprovince!=0) {
	    	nombre=nombre+" where e.codeprovince="+codeprovince+" and e.codesection="+codesection;
	    } 
	    if (!search.equals("0")&&codesection!=0 && codeprovince!=0) {
	    	nombre=nombre+" where e.nomentite like'%"+search+"%' and e.codesection="+codesection+" and e.codeprovince="+codeprovince;
	    } 
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query=" with QUERY AS( "
			+ " SELECT count(DISTINCT identifiant)as effectif, e.codeentite,e.codesection,e.codeprovince,e.nomentite,e.etat "
			+ " ,e.datecreation,  "
			+ "ROW_NUMBER() OVER(ORDER BY e.codeentite DESC) as line "
			+ "	FROM tab_entiterem e "
			+ "	inner join tab_situation s on s.codeentite=e.codeentite ";
    if (!search.equals("0")&&codesection==0 && codeprovince==0) {
    	query=query+" where e.nomentite like'%"+search+"%'";
    }    
    if (search.equals("0")&&codesection!=0 && codeprovince==0) {
    	query=query+" where e.codesection ="+codesection;
    } 
    if (search.equals("0")&&codesection==0 && codeprovince!=0) {
    	query=query+" where e.codeprovince ="+codeprovince;
    }  
    if (!search.equals("0")&&codesection!=0 && codeprovince==0) {
    	query=query+" where e.nomentite like'%"+search+"%' and e.codesection="+codesection;
    } if (!search.equals("0")&&codesection==0 && codeprovince!=0) {
    	query=query+" where e.nomentite like'%"+search+"%' and e.codeprovince="+codeprovince;
    } if (search.equals("0")&&codesection!=0 && codeprovince!=0) {
    	query=query+" where e.codeprovince="+codeprovince+" and e.codesection="+codesection;
    } 
    if (!search.equals("0")&&codesection!=0 && codeprovince!=0) {
    	query=query+" where e.nomentite like'%"+search+"%' and e.codesection="+codesection+" and e.codeprovince="+codeprovince;
    } 
    query=query+ " group by e.codeentite,e.codesection,e.codeprovince,e.nomentite,e.etat,e.datecreation ";
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));

return new PageImpl<identificationModel>(collections,pageable,count);
	}
}
