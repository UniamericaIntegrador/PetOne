package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.System_logs;
import app.service.System_logsService;

@RestController
@CrossOrigin("*")
public class System_logsController {
	@Autowired
	private System_logsService service;
	
	@GetMapping("/logs")
	public ResponseEntity<List<System_logs>> listAll(){
		try {
			List<System_logs> lista = this.service.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
