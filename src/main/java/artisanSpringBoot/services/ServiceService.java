package artisanSpringBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.ServiceRepository;


@Service
public class ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	public boolean save(model.Service s) {
		model.Service ServiceBase = null;
		if (s.getIdService() != null) {
			Optional<model.Service> opt = serviceRepository.findByIdService(s.getIdService()); // optionnal : renvois un objet
			if (opt.isPresent()) {
				ServiceBase = opt.get();
				ServiceBase.setNomService((s.getNomService() != null) ? s.getNomService() : ServiceBase.getNomService());
				ServiceBase.setMetier((s.getMetier() != null) ? s.getMetier() : ServiceBase.getMetier());
				return true;
			}
		} else {
			Boolean erreur = false;
			if (s.getNomService() == null) {
				erreur = true;
			}
			if (s.getMetier() == null) {
				erreur = true;
			}
			
			if (!erreur) {
				serviceRepository.save(s);
				return true;
			}
		}
		//moi.save(ServiceBase);
		return false;

	}

}
