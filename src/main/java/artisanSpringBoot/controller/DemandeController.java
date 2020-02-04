package artisanSpringBoot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import artisanSpringBoot.model.Demande;
import artisanSpringBoot.model.StatutDemande;
import artisanSpringBoot.repositories.DemandeRepository;

@Controller
@RequestMapping("/demande")
public class DemandeController {

	@Autowired
	DemandeRepository demandeRepository;
	
	@GetMapping("/listdemande")
	public ModelAndView list() {
		return new ModelAndView("demande/listdemande", "demandes", demandeRepository.findAll());
	}
	
	@GetMapping("/editdemande")
	public String edit(@RequestParam(name="idDemande") Long idDemande, Model model) {
	Optional<Demande> opt=demandeRepository.findById(idDemande);
	Demande d=null;
	if (opt.isPresent()) {
		d=opt.get();
	}
	model.addAttribute("demande", d);
	return goEdit(d, model);
	}
	
	private String goEdit(Demande d, Model model) {
		
		model.addAttribute("demande", d);
		model.addAttribute("statuts", StatutDemande.values());

		return "demande/editdemande";
}
//	@PostMapping("/savedemande")
//	public String saveDemande(@ModelAttribute Demande demande, Model model) {
//		return save(demande, model);
//	}
	
//	public String save( Demande demande, Model model) {
//		demandeRepository.save(demande);
//		return "redirect:/demande/listdemande";
//	}
	
	@GetMapping("/addDemande")
	public String addDemande(@ModelAttribute Demande demande, Model model) {
		return goEdit(new Demande(), model);
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam (name="idDemande") Long idDemande) {
		demandeRepository.deleteById(idDemande);
		return "redirect:/demande/listdemande";
	}
	
	@PostMapping("/savedemande")
	public ModelAndView save(@ModelAttribute("demande") Demande demande){
		demandeRepository.save(demande);
		return new ModelAndView("redirect:/demande/listdemande");
	}
	}

