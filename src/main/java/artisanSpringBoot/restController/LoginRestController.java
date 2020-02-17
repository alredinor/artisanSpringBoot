package artisanSpringBoot.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"})
public class LoginRestController {

	@GetMapping("/rest/login")
	public ResponseEntity<Void> login(){

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}
