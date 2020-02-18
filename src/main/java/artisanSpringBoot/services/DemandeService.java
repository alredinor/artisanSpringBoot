package artisanSpringBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import artisanSpringBoot.model.Demande;
import artisanSpringBoot.repositories.DemandeRepository;

@Service
public class DemandeService {
	
	@Autowired
	private DemandeRepository demandeRepository;
	
	public boolean save(Demande d) {
		Demande demandeBase=null;
		if(d.getIdDemande()!=null) {
			
			Optional<Demande> opt=demandeRepository.findById(d.getIdDemande());
			//on verifie si l'objet existe
			if(opt.isPresent()) {
				demandeBase=opt.get();
				
				//on verifie l'idDemande
			
				demandeBase.setArtisan((d.getArtisan()!=null)?d.getArtisan():demandeBase.getArtisan());
				demandeBase.setClient((d.getClient()!=null)?d.getClient():demandeBase.getClient());
				demandeBase.setDate((d.getDate()!=null)?d.getDate():demandeBase.getDate());
				demandeBase.setMessage((d.getMessage()!=null)?d.getMessage():demandeBase.getMessage());
				demandeBase.setMetier((d.getMetier()!=null)?d.getMetier():demandeBase.getMetier());
				demandeBase.setService((d.getService()!=null)?d.getService():demandeBase.getService());
				demandeBase.setStatut((d.getStatut()!=null)?d.getStatut():demandeBase.getStatut());
				
				
				
				demandeRepository.save(demandeBase);
				return true;
			}
			
		}else {
			boolean erreur=false;
			
			if (d.getArtisan() == null) 
			{
				erreur = true;
			}
			if (d.getClient() == null) 
			{
				erreur = true;
			}
			if (d.getDate() == null) 
			{
				erreur = true;
			}
			if (d.getMessage() == null) 
			{
				erreur = true;
			}
			if (d.getMetier() == null) 
			{
				erreur = true;
			}
			if (d.getService() == null) 
			{
				erreur = true;
			}
			if (d.getStatut() == null) 
			{
				erreur = true;
			}
			if (!erreur)
			{
				demandeRepository.save(d);
				return true;
			}
		}
		return false;
	}

}
