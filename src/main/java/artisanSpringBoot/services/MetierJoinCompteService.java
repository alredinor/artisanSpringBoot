package artisanSpringBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import artisanSpringBoot.model.MetierJoinCompte;
import artisanSpringBoot.repositories.MetierJoinCompteRepository;

@Service
public class MetierJoinCompteService {

	@Autowired
	private MetierJoinCompteRepository metierjoincompte;

	public boolean save(MetierJoinCompte mjc) 
	{
		MetierJoinCompte mjcBase = null;
		if (mjc.getId() != null) 
		{
			Optional<MetierJoinCompte> opt = metierjoincompte.findById(mjc.getId()); // optionnal : renvois un objet
			if (opt.isPresent())
			{
				mjcBase = opt.get();
				mjcBase.setCompte((mjc.getCompte() != null) ? mjc.getCompte() : mjcBase.getCompte());
				mjcBase.setMetier((mjc.getMetier() != null) ? mjc.getMetier() : mjcBase.getMetier());
				return true;
			}
		} 
		else 
		{
			Boolean erreur = false;
			if (mjc.getCompte() == null) 
			{
				erreur = true;
			}
			if (mjc.getMetier() == null) 
			{
				erreur = true;
			}
			if (erreur) 
			{
				metierjoincompte.save(mjc);
				return true;
			}
		}
		//moi.save(personneBase);
		return false;

	}

}
