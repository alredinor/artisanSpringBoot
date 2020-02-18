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

import artisanSpringBoot.model.Offre;
import artisanSpringBoot.model.jsonview.JsonViews;
import artisanSpringBoot.repositories.OffreRepository;

@RestController
@RequestMapping("/rest/offre")
@CrossOrigin(origins= {"*"})
public class OffreRestController {

	@Autowired
	private OffreRepository offreRepository;
	
	@GetMapping({ "","/"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Offre>> list(){
		return new ResponseEntity<List<Offre>>(offreRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Offre> FindByKey(@PathVariable("id") Integer id){
		Optional<Offre> opt = offreRepository.findById(id);
		if(opt.isPresent()) {
			return new ResponseEntity<Offre>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		Optional<Offre> opt = offreRepository.findById(id);
		if( opt.isPresent()) {
			offreRepository.deleteById(id);
			return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
	}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping({"/addOffre"})	
	public ResponseEntity<Void> addoffre (@RequestBody Offre offre, UriComponentsBuilder uCB){
	
		offreRepository.save(offre);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/offre/{id}").buildAndExpand(offre.getIdOffre()).toUri());
		return new ResponseEntity<> (headers, HttpStatus.CREATED);
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateoffre (@PathVariable("id")Integer id, @RequestBody Offre d){
	
		Optional <Offre> opt = offreRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Offre offreEnBase = opt.get();
		offreEnBase.setArtisan((d.getArtisan()!=null)?d.getArtisan():offreEnBase.getArtisan());
		offreEnBase.setMetier((d.getMetier()!=null)?d.getMetier():offreEnBase.getMetier());
		offreEnBase.setService((d.getService()!=null)?d.getService():offreEnBase.getService());

	
		offreRepository.save(offreEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
