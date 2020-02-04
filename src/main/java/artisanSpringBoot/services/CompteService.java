package artisanSpringBoot.services;

import java.util.Optional;

import model.Compte;
import repository.CompteRepository;

public class CompteService 
{
	private CompteRepository compte;

	public boolean save(Compte c) 
	{
		Compte compteBase = null;
		if (c.getIdCompte() != null) 
		{
			Optional<Compte> opt = compte.findById(c.getIdCompte()); // optionnal : renvois un objet
			if (opt.isPresent()) {
				compteBase = opt.get();
				compteBase.setLogin((c.getLogin() != null) ? c.getLogin() : compteBase.getLogin());
				compteBase.setMdp((c.getMdp() != null) ? c.getMdp() : compteBase.getMdp());
				compteBase.setEmail((c.getEmail() != null) ? c.getEmail() : compteBase.getEmail());
				compteBase.setAdresse((c.getAdresse() != null) ? c.getAdresse() : compteBase.getAdresse());
				return true;
			}
		}
		else 
		{
			Boolean erreur = false;
			if (c.getLogin() == null) 
			{
				erreur = true;
			}
			if (c.getMdp() == null) 
			{
				erreur = true;
			}
			if (c.getEmail() == null) 
			{
				erreur = true;
			}
			if (c.getAdresse() == null) 
			{
				erreur = true;
			}
			if (erreur)
			{
				compte.save(c);
				return true;
			}
		}
		//moi.save(personneBase);
		return false;
	}
}
