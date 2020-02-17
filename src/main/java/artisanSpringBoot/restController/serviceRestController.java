package artisanSpringBoot.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import artisanSpringBoot.model.Service;
import artisanSpringBoot.model.jsonview.JsonViews;
import artisanSpringBoot.model.jsonview.ServiceWithMetier;
import artisanSpringBoot.repositories.ServiceRepository;


@RestController
@RequestMapping("/rest/service")
@CrossOrigin(origins = {"*"})
public class serviceRestController {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@GetMapping({"","/"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Service>> list(){
		return new ResponseEntity<List<Service>>(serviceRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/metier")
	@JsonView(ServiceWithMetier.class)
	public ResponseEntity<List<Service>> listWithMetier(){
		return new ResponseEntity<List<Service>>(serviceRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{idService}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Service> findByKey(@PathVariable("idService") Integer nomService){
		
		Optional<Service> opt=serviceRepository.findByIdService(nomService);
		if(opt.isPresent()) {
			return new ResponseEntity<Service>(opt.get(),HttpStatus.OK);	
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping({"","/"})
	public ResponseEntity<Void> addService(@RequestBody Service service, UriComponentsBuilder uCB) {
		
		Optional<Service> opt=serviceRepository.findByIdService(service.getIdService());
		if(opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		serviceRepository.save(service);
		
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(uCB.path("/rest/service/{idService}").buildAndExpand(service.getNomService()).toUri());
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{idService}")
	public ResponseEntity<Void> delete(@PathVariable("idService") Integer nomService){
		Optional<Service> opt=serviceRepository.findByIdService(nomService);
		if(opt.isPresent()) {
			serviceRepository.deleteById(nomService);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//update d'un service à partir de son id
	@PutMapping("/{idService}")
	public ResponseEntity<Void> update(@PathVariable("idService") Integer idService, @RequestBody @Valid Service s, BindingResult br){
		return updateService(idService, s,br);
	}
	
	public ResponseEntity<Void> updateService(Integer idService, Service s, BindingResult br){
		Optional<Service> opt=serviceRepository.findByIdService(idService);
		if(opt.isPresent()) {
			if(br.hasErrors()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Service serviceEnBase=opt.get();
			serviceEnBase.setNomService(s.getNomService()==null || s.getNomService().isEmpty() ? serviceEnBase.getNomService():s.getNomService());
			serviceRepository.save(serviceEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
