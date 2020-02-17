package artisanSpringBoot.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import artisanSpringBoot.model.Artisan;
import artisanSpringBoot.model.Client;
import artisanSpringBoot.model.Compte;
import artisanSpringBoot.repositories.CompteRepository;

@Controller
@RequestMapping("/compte")
public class CompteController {

	@Autowired
	CompteRepository compteRepository;

//METHODE FINDALL	
	
	@GetMapping("/compteList")
	public String list(Model model) {
		model.addAttribute("compte", compteRepository.findAll());
		return "compte/compteList";

	}
	
//METHODE goEDIT
	
	private String goEdit(Compte c, Model model) {

		model.addAttribute("compte", c);
		// model.addAttribute("demandes", demandeRepository.findAll());
		return "compte/compteEdit";
	}

	@GetMapping("/compteEdit")
	public String edit(@RequestParam(name = "idCompte") Integer id, Model model) {
		Optional<Compte> opt = compteRepository.findById(id);
		Compte c = null;
		if (opt.isPresent()) {
			c = opt.get();
		}

		return goEdit(c, model);
	}

//METHODE ADD	
	
	@GetMapping("/addClient")
	public String addClient(Model model) {

		return goEdit(new Client(), model);
	}

	@GetMapping("/addArtisan")
	public String addArtisan(Model model) {

		return goEdit(new Artisan(), model);
	}
//METHODE SAVE

	public String save(Compte compte, BindingResult br, Model model) {
		if(br.hasErrors()) {
			return "compte/compteEdit";
		}
//		if(compte.getDemande()!=null && compte.getDemande().getNom().isEmpty()) {
//			compte.setDemande(null);
//		}
		compteRepository.save(compte);
		return "redirect:/compte/compteList";
	}

	@PostMapping("/saveArtisan")
	public String saveArtisan(@ModelAttribute("compte") @Valid Artisan compte, BindingResult br, Model model) {
		return save(compte, br, model);
	}

	@PostMapping("/saveClient")
	public String saveClient(@ModelAttribute("compte") @Valid Client compte, BindingResult br, Model model) {
		return save(compte, br, model);
	}

//METHODE EDIT

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "idCompte") Integer id) {
		compteRepository.deleteById(id);
		return "redirect:/compte/compteList";
	}
}
