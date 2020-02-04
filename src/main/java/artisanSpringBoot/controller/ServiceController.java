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


import model.Service;
import repository.ServiceRepository;

@Controller
@RequestMapping("/service")
public class ServiceController {

	
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@GetMapping("/listservice")
	public ModelAndView list() {
		return new ModelAndView("service/listservice", "services", serviceRepository.findAll());
	}
	
//	@GetMapping("/listsalle")
//	public String listsalle(Model model) {
//		model.addAttribute("salles",salleRepository.findAll());
//
//		return "salle/listsalle";
//	}
	
//	@GetMapping("/editsalle")
//	public String edit(@RequestParam(name="nom") String nom,Model model) {
//		Optional<Salle> opt= salleRepository.findById(nom);
//		Salle s=null;
//		if(opt.isPresent()) {
//			s=opt.get();
//		}
//		return goEdit(s, model);
//	}
//	
	@GetMapping("/delete")
	public  ModelAndView delete(@RequestParam (name="idService") long idService) {
		serviceRepository.deleteById(idService);
		return new ModelAndView("redirect:/service/listservice");
	}
	
	@GetMapping("/addservice")
	public ModelAndView addservice() {
		return new ModelAndView("service/addservice", "service", new Service());
		
	}
	@GetMapping("/saveservice")
	public ModelAndView save(@ModelAttribute("service") Service service){
		serviceRepository.save(service);
		return new ModelAndView("redirect:/service/listservice");
	}
	
//	@GetMapping("/addsalle")
//	public String addsalle(@ModelAttribute Salle s, Model model) {
//		return goEdit(new Salle(), model);
//		
//	}
//	
//	@GetMapping("/delete")
//	public String delete(@RequestParam (name="nom") String nom) {
//		salleRepository.deleteById(nom);
//		return "redirect:/salle/listsalle";
//	}
//	
//	@PostMapping("/savesalle")
//	public String saveSalle(@ModelAttribute Salle salle, Model model) {
//		return save(salle, model);
//	}
//	
//	
//	
//	public String save( Salle salle, Model model) {
//		salleRepository.save(salle);
//		return "redirect:/salle/listsalle";
//	}
//	
//	private String goEdit(Salle s, Model model) {
//		
//		model.addAttribute("salle", s);
//
//		return "salle/addsalle";
//}
	}

