package formation.sopra.formationSpringBoot.restController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import artisanSpringBoot.model.Salle;
import artisanSpringBoot.model.jsonview.JsonViews;
import artisanSpringBoot.model.jsonview.JsonViews.Common;
import artisanSpringBoot.repositories.SalleRepository;

@RestController
@RequestMapping("/rest/salle")
public class SalleRestController {

	@Autowired
	private SalleRepository salleRepository;

	@GetMapping({ "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Salle>> list() {
		return new ResponseEntity<List<Salle>>(salleRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/personne")
	@JsonView(JsonViews.SalleWithPersonne.class)
	public ResponseEntity<List<Salle>> listWithPersonne() {
		return new ResponseEntity<List<Salle>>(salleRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Salle> findByKey(@PathVariable("id") String nom) {
		Optional<Salle> opt = salleRepository.findById(nom);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}/personne")
	@JsonView(JsonViews.SalleWithPersonne.class)
	public ResponseEntity<Salle> findByKeyWithPersonne(@PathVariable("id") String nom) {
		Optional<Salle> opt = salleRepository.findById(nom);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addSalle(@RequestBody Salle salle, UriComponentsBuilder uCB) {
		Optional<Salle> opt = salleRepository.findById(salle.getNom());
		if (opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		salleRepository.save(salle);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/salle/{id}").buildAndExpand(salle.getNom()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String nom) {
		Optional<Salle> opt = salleRepository.findById(nom);
		if (opt.isPresent()) {
			salleRepository.deleteById(nom);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//update
//	@PutMapping("/{id}")
//	public ResponseEntity<Salle> update(@PathVariable("id")String nom,@RequestBody Salle salle){
//		Optional<Salle> opt=salleRepository.findById(nom);
//		if(opt.isPresent()) {
//			Salle salleEnBase=opt.get();
//			salleRepository.save(salleEnBase)
//		}
//	}

}