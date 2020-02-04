package artisanSpringBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Demande;
import repository.DemandeRepository;

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
				demandeBase.setIdDemande((d.getIdDemande()!=null)?d.getIdDemande():demandeBase.getIdDemande());
				
				demandeRepository.save(demandeBase);
				return true;
			}
			
		}else {
			boolean erreur=false;
			if(d.getIdDemande()==null) {
				return true;
			}
			if(!erreur){
				demandeRepository.save(d);
				return true;
			}
		}
		return false;
	}

}
