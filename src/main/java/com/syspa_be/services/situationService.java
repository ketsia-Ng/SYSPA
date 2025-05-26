package com.syspa_be.services;

import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.syspa_be.entity.situation;
import com.syspa_be.interfaces.isituation;
import com.syspa_be.models.identificationModel;
import com.syspa_be.models.reportBase64;
import com.syspa_be.repository.situationRepostory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class situationService implements isituation {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	situationRepostory situationRepostory;
	
	@Override
	public Boolean save(situation model) {
		Boolean rep=false;
		Long id=model.getId()==null?0:model.getId();
		Optional<situation>exist=situationRepostory.findById(id);
		if(exist.isPresent()) {
			exist.get().setIdentifiant(model.getIdentifiant());
			exist.get().setCodebanque(model.getCodebanque());
			exist.get().setCodeentite(model.getCodeentite());
			exist.get().setCodefonction(model.getCodefonction());
			exist.get().setCodeposition(model.getCodeposition());
			exist.get().setCodeprovince(model.getCodeprovince());
			exist.get().setCodeunite(model.getCodeunite());
			exist.get().setValidation(model.getValidation());
			exist.get().setCodesection(model.getCodesection());
			exist.get().setCodebarem(model.getCodebarem());
			if(situationRepostory.save(exist.get())!=null) {
				rep=true;
			}
		}else {
			if(situationRepostory.save(model)!=null) {
				rep=true;
			}
		}
		return rep;
	}

	@Override
	public Page<identificationModel> collectionssituations(Long identidfiant, Integer validation,Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+  "from tab_situation s "
				+ "inner join tab_banque b on b.codebanque=s.codebanque "
				+ "inner join  tab_section se on se.codesection=s.codesection "
				+ "inner join tab_province p on p.codeprovince=s.codeprovince "
				+ "inner join tab_entiterem e on e.codeentite=s.codeentite "
				+ "inner join tab_unite u on u.codeunite=s.codeunite "
				+ "inner join tab_position po on po.codeposition=s.codeposition "
				+ "inner join tab_fonction f on f.codefonction=s.codefonction "
				+ "inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
				+ "where s.identifiant="+identidfiant+" and s.validation="+validation;
	
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"select s.id,s.identifiant,s.codesection, "
			+ "se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
			+ "e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
			+ "po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,br.codebarem,br.libelle,sum(br.montant) as montant, "
			+ "ROW_NUMBER() OVER(ORDER BY s.id DESC) as line "
			+ "from tab_situation s "
			+ "inner join tab_banque b on b.codebanque=s.codebanque "
			+ "inner join  tab_section se on se.codesection=s.codesection "
			+ "inner join tab_province p on p.codeprovince=s.codeprovince "
			+ "inner join tab_entiterem e on e.codeentite=s.codeentite "
			+ "inner join tab_unite u on u.codeunite=s.codeunite "
			+ "inner join tab_position po on po.codeposition=s.codeposition "
			+ "inner join tab_fonction f on f.codefonction=s.codefonction "
			+ "inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
			+ "where s.identifiant="+identidfiant+" and s.validation="+validation;
    query+=" group by s.id,s.identifiant,s.codesection,se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
    		+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
    		+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,br.codebarem,br.libelle ";
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
return new PageImpl<identificationModel>(collections,pageable,count);
	}

	@Override
	public void validationsituations(Long idsituation, Integer validation) {
		situation s=situationRepostory.findById(idsituation).get();
		s.setValidation(validation);
		situationRepostory.save(s);
	}

	@Override
	public Page<identificationModel> collectionssituationsection(Long identidfiant, Long codesection,
			Integer validation, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+  "from tab_situation s "
				+ "inner join tab_banque b on b.codebanque=s.codebanque "
				+ "inner join  tab_section se on se.codesection=s.codesection "
				+ "inner join tab_province p on p.codeprovince=s.codeprovince "
				+ "inner join tab_entiterem e on e.codeentite=s.codeentite "
				+ "inner join tab_unite u on u.codeunite=s.codeunite "
				+ "inner join tab_position po on po.codeposition=s.codeposition "
				+ "inner join tab_fonction f on f.codefonction=s.codefonction  "
				+ "inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
				+ "where s.identifiant="+identidfiant+" and s.validation="+validation+" and s.codesection="+codesection;
	
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"select s.id,s.identifiant,s.codesection, "
			+ "se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
			+ "e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
			+ "po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,br.codebarem,br.libelle,sum(br.montant) as montant, "
			+ "ROW_NUMBER() OVER(ORDER BY s.id DESC) as line "
			+ "from tab_situation s "
			+ "inner join tab_banque b on b.codebanque=s.codebanque "
			+ "inner join  tab_section se on se.codesection=s.codesection "
			+ "inner join tab_province p on p.codeprovince=s.codeprovince "
			+ "inner join tab_entiterem e on e.codeentite=s.codeentite "
			+ "inner join tab_unite u on u.codeunite=s.codeunite "
			+ "inner join tab_position po on po.codeposition=s.codeposition "
			+ "inner join tab_fonction f on f.codefonction=s.codefonction  "
			+ "inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
			+ "where s.identifiant="+identidfiant+" and s.validation="+validation+" and s.codesection="+codesection;
    query+=" group by s.id,s.identifiant,s.codesection, "
    		+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
    		+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
    		+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,br.codebarem,br.libelle ";
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
return new PageImpl<identificationModel>(collections,pageable,count);
	}

	@Override
	public Page<identificationModel> collectionsadminsituationsection(Long identidfiant,
			Long codeentite, Integer validation, Pageable pageable) {
		String nombre="SELECT COUNT(*)"
				+  "from tab_situation s "
				+ "inner join tab_banque b on b.codebanque=s.codebanque "
				+ "inner join  tab_section se on se.codesection=s.codesection "
				+ "inner join tab_province p on p.codeprovince=s.codeprovince "
				+ "inner join tab_entiterem e on e.codeentite=s.codeentite "
				+ "inner join tab_unite u on u.codeunite=s.codeunite "
				+ "inner join tab_position po on po.codeposition=s.codeposition "
				+ "inner join tab_fonction f on f.codefonction=s.codefonction "
				+ "inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
				+ "where s.identifiant="+identidfiant+" and s.validation="+validation+" and s.codeentite="+codeentite;
	
		Integer count = jdbcTemplate.queryForObject(nombre,Integer.class);
    String query="with QUERY AS("
			+"select s.id,s.identifiant,s.codesection, "
			+ "se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
			+ "e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
			+ "po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,br.codebarem,br.libelle,sum(br.montant) as montant, "
			+ "ROW_NUMBER() OVER(ORDER BY s.id DESC) as line "
			+ "from tab_situation s "
			+ "inner join tab_banque b on b.codebanque=s.codebanque "
			+ "inner join  tab_section se on se.codesection=s.codesection "
			+ "inner join tab_province p on p.codeprovince=s.codeprovince "
			+ "inner join tab_entiterem e on e.codeentite=s.codeentite "
			+ "inner join tab_unite u on u.codeunite=s.codeunite "
			+ "inner join tab_position po on po.codeposition=s.codeposition "
			+ "inner join tab_fonction f on f.codefonction=s.codefonction "
			+ "inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
			+ "where s.identifiant="+identidfiant+" and s.validation="+validation+" and s.codeentite="+codeentite;
    query+=" group by s.id,s.identifiant,s.codesection, "
    		+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
    		+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
    		+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,br.codebarem,br.libelle ";
query=query+") SELECT TOP " + pageable.getPageSize()+" * FROM QUERY WHERE LINE > (" + pageable.getPageNumber() + ") *" + pageable.getPageSize();

List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
return new PageImpl<identificationModel>(collections,pageable,count);
	}
	@Override
	public ResponseEntity<?> impressionlistidentifsection(Long codesection) {
		try {
			
		String query="select s.identifiant,s.codesection, "
				+ " se.nomsection,i.matricule, "
				+ " i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement, i.lieunaiss,"
				+ " br.codebarem,br.libelle,sum(br.montant) as montant "
				+ " from tab_situation s "
				+ " inner join  tab_section se on se.codesection=s.codesection "
				+ " inner join tab_identification i on i.identifiant=s.identifiant "
				+ "inner join tab_barem br on  br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
				+ " where s.validation=1 and s.codesection="+codesection
				+ " group by s.identifiant,s.codesection, se.nomsection,i.matricule,br.codebarem,br.libelle, "
				+ " i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement, i.lieunaiss";
		List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
		JRBeanCollectionDataSource ds=new JRBeanCollectionDataSource(collections);
		JasperPrint reportlist=JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:etats//listsection.jrxml").getAbsolutePath()),null, ds);
		String encodedString=Base64.getEncoder().encodeToString(JasperExportManager.exportReportToPdf(reportlist));
		return ResponseEntity.ok(new reportBase64(encodedString)) ;
		}catch(FileNotFoundException e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
		catch(JRException e) {
			return ResponseEntity.ok().body(e.getMessage());
		
	}
		}

	@Override
	public ResponseEntity<?> impressionlistidentifentite(Long codeentite) {
		try {
			
		String query="select s.identifiant,s.codesection, "
				+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
				+ " e.nomentite,i.matricule, "
				+ "	i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement,i.lieunaiss, "
				+ " br.codebarem,br.libelle,sum(br.montant) as montant "
				+ " from tab_situation s "
				+ " inner join  tab_section se on se.codesection=s.codesection "
				+ " inner join tab_province p on p.codeprovince=s.codeprovince "
				+ " inner join tab_entiterem e on e.codeentite=s.codeentite "
				+ " inner join tab_identification i on i.identifiant=s.identifiant "
				+ " inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
				+ " where s.validation=1 and s.codeentite="+codeentite
				+ " group by s.identifiant,s.codesection,se.nomsection,s.codeprovince,p.nomprovince,s.codeentite,br.codebarem,br.libelle, "
				+ " e.nomentite,i.matricule,i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement,i.lieunaiss ";
		List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
		JRBeanCollectionDataSource ds=new JRBeanCollectionDataSource(collections);
		JasperPrint reportlist=JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:etats//listsectionentite.jrxml").getAbsolutePath()),null, ds);
		String encodedString=Base64.getEncoder().encodeToString(JasperExportManager.exportReportToPdf(reportlist));
		return ResponseEntity.ok(new reportBase64(encodedString)) ;
		}catch(FileNotFoundException e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
		catch(JRException e) {
			return ResponseEntity.ok().body(e.getMessage());
	}
	}

	@Override
	public ResponseEntity<?> impressionpersidentifentite(Long codeentite, Long identifiant) {
		try {
			
			String query="select s.id,s.identifiant,s.codesection, "
					+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
					+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
					+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,i.matricule,"
					+ "	i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement,i.lieunaiss, "
					+ " br.codebarem,br.libelle,sum(br.montant) as montant "
					+ " from tab_situation s "
					+ " inner join tab_banque b on b.codebanque=s.codebanque "
					+ " inner join  tab_section se on se.codesection=s.codesection "
					+ " inner join tab_province p on p.codeprovince=s.codeprovince "
					+ " inner join tab_entiterem e on e.codeentite=s.codeentite "
					+ " inner join tab_unite u on u.codeunite=s.codeunite "
					+ " inner join tab_position po on po.codeposition=s.codeposition "
					+ " inner join tab_fonction f on f.codefonction=s.codefonction "
					+ " inner join tab_identification i on i.identifiant=s.identifiant "
					+ " inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
					+ " where s.codeentite="+codeentite+" and s.identifiant="+identifiant;
			query+= " group by s.id,s.identifiant,s.codesection, "
					+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
					+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
					+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,i.matricule, "
					+ "	i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement,i.lieunaiss, "
					+ " br.codebarem,br.libelle";
			List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
			JRBeanCollectionDataSource ds=new JRBeanCollectionDataSource(collections);
			JasperPrint reportlist=JasperFillManager.fillReport(
					JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:etats//perssectionentite.jrxml").getAbsolutePath()),null, ds);
			String encodedString=Base64.getEncoder().encodeToString(JasperExportManager.exportReportToPdf(reportlist));
			return ResponseEntity.ok(new reportBase64(encodedString)) ;
			}catch(FileNotFoundException e) {
				return ResponseEntity.ok().body(e.getMessage());
			}
			catch(JRException e) {
				return ResponseEntity.ok().body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> impressionpersidentifsection(Long codesection, Long identifiant) {
		try {
			
		String query="select s.id,s.identifiant,s.codesection, "
				+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
				+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
				+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,i.matricule,"
				+ " i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement, i.lieunaiss,"
				+ " br.codebarem,br.libelle,sum(br.montant)as montant "
				+ " from tab_situation s "
				+ " inner join tab_banque b on b.codebanque=s.codebanque "
				+ " inner join  tab_section se on se.codesection=s.codesection "
				+ " inner join tab_province p on p.codeprovince=s.codeprovince "
				+ " inner join tab_entiterem e on e.codeentite=s.codeentite "
				+ " inner join tab_unite u on u.codeunite=s.codeunite "
				+ " inner join tab_position po on po.codeposition=s.codeposition "
				+ " inner join tab_fonction f on f.codefonction=s.codefonction "
				+ " inner join tab_identification i on i.identifiant=s.identifiant "
				+ " inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
				+ " where s.codesection="+codesection+" and s.identifiant="+identifiant;
		query+=" group by s.id,s.identifiant,s.codesection, "
				+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
				+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
				+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,i.matricule, "
				+ " i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement, i.lieunaiss, "
				+ " br.codebarem,br.libelle";
		List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
		JRBeanCollectionDataSource ds=new JRBeanCollectionDataSource(collections);
		JasperPrint reportlist=JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:etats//perssection.jrxml").getAbsolutePath()),null, ds);
		String encodedString=Base64.getEncoder().encodeToString(JasperExportManager.exportReportToPdf(reportlist));
		return ResponseEntity.ok(new reportBase64(encodedString)) ;
		}catch(FileNotFoundException e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
		catch(JRException e) {
			return ResponseEntity.ok().body(e.getMessage());
		
	}
	}

	@Override
	public ResponseEntity<?> affichersituation(Long id) {
		try {
			
			String query="select s.id,s.identifiant,s.codesection, "
					+ " se.nomsection,s.codeprovince,p.nomprovince,s.codeentite, "
					+ " e.nomentite,s.codeunite,u.nomunite,s.codeposition,s.validation, "
					+ " po.nomposition,s.codefonction,f.nomfonction,s.codebanque,b.nombanque,i.matricule,"
					+ " i.nom,i.postnom,i.prenom,i.genre,i.datenaiss,i.dateengagement, i.lieunaiss,"
					+ " br.codebarem,br.libelle,br.montant "
					+ " from tab_situation s "
					+ " inner join tab_banque b on b.codebanque=s.codebanque "
					+ " inner join  tab_section se on se.codesection=s.codesection "
					+ " inner join tab_province p on p.codeprovince=s.codeprovince "
					+ " inner join tab_entiterem e on e.codeentite=s.codeentite "
					+ " inner join tab_unite u on u.codeunite=s.codeunite "
					+ " inner join tab_position po on po.codeposition=s.codeposition "
					+ " inner join tab_fonction f on f.codefonction=s.codefonction "
					+ " inner join tab_identification i on i.identifiant=s.identifiant "
					+ " inner join tab_barem br on   br.codebarem=s.codebarem  and br.codefonction=s.codefonction "
					+ " where s.id="+id;
			List<identificationModel> collections = jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(identificationModel.class));
			String quer="select d.intitule,d.montant,d.sigle "
					+ " from tab_detailbarem d "
					+ " where d.codebarem="+collections.get(0).getCodebarem();
			JRBeanCollectionDataSource coldetail=new JRBeanCollectionDataSource(jdbcTemplate.query(quer,BeanPropertyRowMapper.newInstance(identificationModel.class)));
			Map<String, Object> emparams = new HashMap<String, Object>();
			emparams.put("detailbarem", coldetail);
			JRBeanCollectionDataSource ds=new JRBeanCollectionDataSource(collections);
			JasperPrint reportlist=JasperFillManager.fillReport(
					JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:etats//fichesituation.jrxml").getAbsolutePath()),emparams, ds);
			String encodedString=Base64.getEncoder().encodeToString(JasperExportManager.exportReportToPdf(reportlist));
			return ResponseEntity.ok(new reportBase64(encodedString)) ;
			}catch(FileNotFoundException e) {
				return ResponseEntity.ok().body(e.getMessage());
			}
			catch(JRException e) {
				return ResponseEntity.ok().body(e.getMessage());
			
		}
	}
}
