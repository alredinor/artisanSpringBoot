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

import artisanSpringBoot.model.Metier;
import artisanSpringBoot.model.jsonview.JsonViews;
import artisanSpringBoot.repositories.MetierRepository;


@RestController
@RequestMapping("/rest/metier")
@CrossOrigin(origins = {"*"})
public class MetierRestController 
{
	@Autowired
	private MetierRepository metierRepository;
	
	@GetMapping({"","/"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Metier>> list()
	{
		return new ResponseEntity<List<Metier>> (metierRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping({"/addMetier"})
	public ResponseEntity<Void> addMetier(@RequestBody Metier metier, UriComponentsBuilder uCB)
	{
//		Optional<Metier> opt=metierRepository.findById(metier.getIdMetier());
//		if(opt.isPresent())
//		{
//			return new ResponseEntity<>(HttpStatus.CONFLICT);
//		}
		metierRepository.save(metier);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/metier/{id}").buildAndExpand(metier.getTitreMetier()).toUri());
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Metier> delete(@PathVariable("id") Integer id)
	{
		Optional<Metier> opt = metierRepository.findById(id);
		if(opt.isPresent())
		{
			metierRepository.deleteById(id);
			return new ResponseEntity<Metier>(opt.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public ResponseEntity<Metier> FindByKey(@PathVariable("id") Integer id){
        Optional<Metier> opt = metierRepository.findById(id);
        if(opt.isPresent()) {
            return new ResponseEntity<Metier>(opt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateMetier (@PathVariable("id")Integer id, @RequestBody Metier m){
	
		Optional <Metier> opt = metierRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Metier metierEnBase = opt.get();
		metierEnBase.setIdMetier(m.getIdMetier());
		metierEnBase.setTitreMetier(m.getTitreMetier());
		
		metierRepository.save(metierEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
