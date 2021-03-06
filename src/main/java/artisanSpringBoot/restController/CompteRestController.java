package artisanSpringBoot.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import artisanSpringBoot.model.Artisan;
import artisanSpringBoot.model.Client;
import artisanSpringBoot.model.Compte;
import artisanSpringBoot.model.Role;
import artisanSpringBoot.model.UserRole;
import artisanSpringBoot.model.jsonview.JsonViews;
import artisanSpringBoot.repositories.CompteRepository;
import artisanSpringBoot.repositories.UserRoleRepository;

@RestController
@RequestMapping("/rest/compte")
@CrossOrigin("*")
public class CompteRestController {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@JsonView(JsonViews.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Compte>> findAll() {
		return new ResponseEntity<>(compteRepository.findAll(), HttpStatus.OK);
	}

	private ResponseEntity<Compte> findByKey(Integer id) {
		Optional<Compte> opt = compteRepository.findByIdCompte(id);
		if (opt.isPresent()) {
			// ok
			return new ResponseEntity<Compte>(opt.get(), HttpStatus.OK);
		}
		// pas ok
		return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);
	}

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Compte> findById(@PathVariable("id") Integer id) {
		return findByKey(id);
	}

	
	// A VOIR SI BESOIN DE SORTIR DES TYPES DE COMPTE OU DE ROLE
	
	/*@JsonView(JsonViews.PersonneWithSalle.class)
	@GetMapping("/{id}/salle")
	public ResponseEntity<Compte> findByIdWithSalle(@PathVariable("id") Integer id) {
		return findByKey(id);
	}*/

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Compte> opt = compteRepository.findByIdCompte(id);
		if (opt.isPresent()) {
			compteRepository.deleteByIdCompte(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/artisan")
	public ResponseEntity<Void> insertArtisan(@Valid @RequestBody Artisan artisan, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(artisan, br, uCB);
	}

	@PostMapping("/client")
	public ResponseEntity<Void> insertClient(@Valid @RequestBody Client client, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(client, br, uCB);
	}

	private ResponseEntity<Void> insert(Compte compte, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		compte.setMdp(passwordEncoder.encode(compte.getMdp()));
		compte.setEnable(true);
		compteRepository.save(compte);
		UserRole userRole = new UserRole();
		userRole.setIdCompte(compte);
		userRole.setRole(Role.ROLE_CLIENT);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/Personne/{id}").buildAndExpand(compte.getIdCompte()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/{id}/artisan")
	public ResponseEntity<Void> updateArtisan(@PathVariable("id") Integer id, @Valid @RequestBody Artisan artisan,
			BindingResult br) {
		return update(id, artisan, br);
	}

	@PutMapping("/{id}/client")
	public ResponseEntity<Void> updateClient(@PathVariable("id") Integer id, @Valid @RequestBody Client Eleve,
			BindingResult br) {
		return update(id, Eleve, br);
	}

	private ResponseEntity<Void> update(Integer id, Compte compte, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Compte> opt = compteRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Compte compteEnBase = opt.get();
		compteEnBase.setEmail(compte.getEmail());
		compteEnBase.setLogin(compte.getLogin());
		compteEnBase.setAdresse(compte.getAdresse());
		/*if (compteEnBase instanceof Artisan) {
			((Artisan) compteEnBase).setExperience(((Artisan) compte).getExperience());
		} else {
			((Client) compteEnBase).setFormation(((Client) compte).getFormation());
		}*/
		compteRepository.save(compteEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
