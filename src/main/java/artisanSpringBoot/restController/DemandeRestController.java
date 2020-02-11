package artisanSpringBoot.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import artisanSpringBoot.model.Demande;
import artisanSpringBoot.model.jsonview.JsonViews;
import artisanSpringBoot.repositories.DemandeRepository;


@RestController
@RequestMapping("/rest/demande")
@CrossOrigin(origins= {"*"})
public class DemandeRestController {

	@Autowired
	private DemandeRepository demandeRepository;
	
	@GetMapping({ "","/"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Demande>> list(){
		return new ResponseEntity<List<Demande>>(demandeRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Demande> FindByKey(@PathVariable("id") Long id){
		Optional<Demande> opt = demandeRepository.findById(id);
		if(opt.isPresent()) {
			return new ResponseEntity<Demande>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		Optional<Demande> opt = demandeRepository.findById(id);
		if( opt.isPresent()) {
			demandeRepository.deleteById(id);
			return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
	}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping({"/addDemande"})	
	public ResponseEntity<Void> addDemande (@RequestBody Demande demande, UriComponentsBuilder uCB){
	
		demandeRepository.save(demande);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/demande/{id}").buildAndExpand(demande.getIdDemande()).toUri());
		return new ResponseEntity<> (headers, HttpStatus.CREATED);
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateDemande (@PathVariable("id")Long id, @RequestBody Demande d){
	
		Optional <Demande> opt = demandeRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Demande demandeEnBase = opt.get();
		demandeEnBase.setArtisan(d.getArtisan());
		demandeEnBase.setClient(d.getClient());
		demandeEnBase.setDate(d.getDate());
		demandeEnBase.setMessage(d.getMessage());
		demandeEnBase.setMetier(d.getMetier());
		demandeEnBase.setService(d.getService());
		demandeEnBase.setStatut(d.getStatut());
		
		demandeRepository.save(demandeEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
		
	
	
		
		
		
		
	
	

