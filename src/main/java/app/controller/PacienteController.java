package app.controller;

import java.util.List;

import app.config.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Paciente;
import app.service.PacienteService;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/paciente")
@Validated
@CrossOrigin("*")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	private final SecurityManager securityManager;

	@Autowired
	public PacienteController(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Paciente paciente) {
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.pacienteService.save(paciente, userDetails.getUsername());
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody Paciente paciente, @PathVariable("id") long id) {
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.pacienteService.update(id, paciente, userDetails.getUsername());
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.pacienteService.delete(id, userDetails.getUsername());
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/listAll")
	public ResponseEntity<List<Paciente>> listAll() {
		try {
			//System.out.println(this.email);
			List<Paciente> lista = this.pacienteService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Paciente> findById(@PathVariable("id") long id) {
		try {
			Paciente paciente = this.pacienteService.findById(id);
			return new ResponseEntity<>(paciente, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByPart")
	public ResponseEntity<List<Paciente>> findByPart(@RequestParam String nome) {
		try {
			List<Paciente> lista = this.pacienteService.findByPart(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByRaca")
	public ResponseEntity<List<Paciente>>findByRaca(@RequestParam long id){
		try {
			List<Paciente>lista = this.pacienteService.findByRaca(id);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/*
	@GetMapping("/findByEspecie")
	public ResponseEntity<List<Paciente>>findByEspecie(@RequestParam long id){
		try {
			List<Paciente>lista = this.pacienteService.findByEspecie(id);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	*/

	/*
	@GetMapping("/findByRaca")
	public ResponseEntity<List<Paciente>> findByRaca(@RequestParam("raca") String raca) {
		try {
			List<Paciente> lista = this.pacienteService.findByRaca(raca);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	*/

	/*
	@GetMapping("/findByEspecie")
	public ResponseEntity<List<Paciente>> findByRacaEspecieNome(@RequestParam("nome") String nome) {
		try {
			List<Paciente> lista = this.pacienteService.findByRacaEspecieNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	/*
	@GetMapping("/findByEspecie")
	public ResponseEntity<List<Paciente>>findByRacaEspecie(@RequestParam long id){
		try {
			List<Paciente>lista = this.pacienteService.findByRacaEspecie(id);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	*/

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findByAcimaAno")
	public ResponseEntity<List<Paciente>> findByAcimaAno(@RequestParam int ano) {
		try {
			List<Paciente> lista = this.pacienteService.findByAcimaAno(ano);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		try {
			long counter = this.pacienteService.count();
			return new ResponseEntity<>(counter, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
	}
}
