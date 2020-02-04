package artisanSpringBoot.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import artisanSpringBoot.model.Eleve;
import artisanSpringBoot.model.Formateur;
import artisanSpringBoot.model.Personne;
import artisanSpringBoot.model.jsonview.JsonViews;
import artisanSpringBoot.repositories.PersonneRepository;

@RestController
@RequestMapping("/rest/personne")
public class PersonneRestController {

	@Autowired
	private PersonneRepository personneRepository;

	@JsonView(JsonViews.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Personne>> findAll() {
		return new ResponseEntity<>(personneRepository.findAll(), HttpStatus.OK);
	}

	private ResponseEntity<Personne> findByKey(Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			// ok
			return new ResponseEntity<Personne>(opt.get(), HttpStatus.OK);
		}
		// pas ok
		return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
	}

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Personne> findById(@PathVariable("id") Integer id) {
		return findByKey(id);
	}

	@JsonView(JsonViews.PersonneWithSalle.class)
	@GetMapping("/{id}/salle")
	public ResponseEntity<Personne> findByIdWithSalle(@PathVariable("id") Integer id) {
		return findByKey(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			personneRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/formateur")
	public ResponseEntity<Void> insertFormateur(@Valid @RequestBody Formateur formateur, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(formateur, br, uCB);
	}

	@PostMapping("/eleve")
	public ResponseEntity<Void> insertEleve(@Valid @RequestBody Eleve Eleve, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(Eleve, br, uCB);
	}

	private ResponseEntity<Void> insert(Personne personne, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		personneRepository.save(personne);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/Personne/{id}").buildAndExpand(personne.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/{id}/formateur")
	public ResponseEntity<Void> updateFormateur(@PathVariable("id") Integer id, @Valid @RequestBody Formateur formateur,
			BindingResult br) {
		return update(id, formateur, br);
	}

	@PutMapping("/{id}/eleve")
	public ResponseEntity<Void> updateEleve(@PathVariable("id") Integer id, @Valid @RequestBody Eleve Eleve,
			BindingResult br) {
		return update(id, Eleve, br);
	}

	private ResponseEntity<Void> update(Integer id, Personne personne, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Personne> opt = personneRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Personne personneEnBase = opt.get();
		personneEnBase.setPrenom(personne.getPrenom());
		personneEnBase.setNom(personne.getNom());
		personneEnBase.setCivilite(personne.getCivilite());
		personneEnBase.setDtNaiss(personne.getDtNaiss());
		personneEnBase.setAdresse(personne.getAdresse());
		if (personneEnBase instanceof Formateur) {
			((Formateur) personneEnBase).setExperience(((Formateur) personne).getExperience());
		} else {
			((Eleve) personneEnBase).setFormation(((Eleve) personne).getFormation());
		}
		personneRepository.save(personneEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
