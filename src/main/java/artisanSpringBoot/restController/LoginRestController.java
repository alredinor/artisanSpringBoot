package artisanSpringBoot.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginRestController {

	@GetMapping("/rest/login")
	public ResponseEntity<Void> login(){
System.out.println("dkjfjsdidfl");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}
