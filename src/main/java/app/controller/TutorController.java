package app.controller;

import app.config.SecurityManager;
import app.dto.TutorDTO;
import app.entity.Tutor;
import app.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/tutor")
@Validated
@CrossOrigin("*")
public class TutorController {
	@Autowired
	private TutorService tutorService;

	private final SecurityManager securityManager;

	@Autowired
	public TutorController(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}
	
	/*
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PostMapping("/save")
	public ResponseEntity<String>save(@Valid @RequestBody Tutor tutor){
		try {
			String mensagem = this.tutorService.save(tutor);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@Valid @RequestBody Tutor tutor, @PathVariable("id") long id){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.tutorService.update(id, tutor, userDetails.getUsername());
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	 
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>delete(@PathVariable("id") long id){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.tutorService.delete(id, userDetails.getUsername());
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/activated")
	public ResponseEntity<TutorDTO>logado(){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			return new ResponseEntity<>(this.tutorService.active(userDetails.getUsername()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/listAll")
	public ResponseEntity<List<Tutor>>listAll(){
		try {
			List<Tutor>lista = this.tutorService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Tutor>findById(@PathVariable("id") long id){
		try {
			Tutor tutor = this.tutorService.findById(id);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}


	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findByNome")
	public ResponseEntity<List<Tutor>>findByNome(@RequestParam("nome") String nome){
		try {
			List<Tutor> tutor = this.tutorService.findByNome(nome);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findByCpf")
	public ResponseEntity<List<Tutor>>findByCpf(@RequestParam("cpf") String cpf){
		try {
			List<Tutor> tutor = this.tutorService.findByCpf(cpf);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	@GetMapping("/findByEndereco")
	public ResponseEntity<List<Tutor>>findByEndereco(@RequestParam("endereco") String endereco){
		try {
			List<Tutor> tutor = this.tutorService.findByEndereco(endereco);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findByPacienteNome")
	public ResponseEntity<List<Tutor>>findByPacienteNome(@RequestParam("nome") String nome){
		try {
			List<Tutor> tutor = this.tutorService.findByPacienteNome(nome);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findByTrechoNome")
	public ResponseEntity<List<Tutor>>findByTrechoNome(@RequestParam("nome") String nome){
		try {
			List<Tutor> tutor = this.tutorService.findByTrechoNome(nome);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findByTrechoCpf")
	public ResponseEntity<List<Tutor>>findByTrechoCpf(@RequestParam("cpf") String cpf){
		try {
			List<Tutor> tutor = this.tutorService.findByTrechoCpf(cpf);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		try {
			long counter = this.tutorService.count();
			return new ResponseEntity<>(counter, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
	}
}