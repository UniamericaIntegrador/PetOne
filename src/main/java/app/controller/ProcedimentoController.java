package app.controller;

import java.util.List;

import app.config.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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

import app.entity.Procedimento;
import app.service.ProcedimentoService;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/procedimento")
@Validated
@CrossOrigin("*")
public class ProcedimentoController {

	@Autowired
	private ProcedimentoService procedimentoService;

	private final SecurityManager securityManager;

	@Autowired
	public ProcedimentoController(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@PostMapping("/save")
	public ResponseEntity<String>save(@Valid @RequestBody Procedimento procedimento){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.procedimentoService.save(procedimento, userDetails.getUsername());
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@Valid @RequestBody Procedimento procedimento, @PathVariable("id") long id){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.procedimentoService.update(id, procedimento, userDetails.getUsername());
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>delete(@PathVariable("id") long id){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.procedimentoService.delete(id, userDetails.getUsername());
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/listAll")
	public ResponseEntity<List<Procedimento>>listAll(){
		try {
			List<Procedimento>lista = this.procedimentoService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Procedimento>findById(@PathVariable("id") long id){
		try {
			Procedimento procedimento = this.procedimentoService.findById(id);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByNomeProcedimento")
	public ResponseEntity<List<Procedimento>>findByNomeProcedimento(@RequestParam("nomeProcedimento") String nomeProcedimento){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findByNomeProcedimento(nomeProcedimento);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		try {
			long counter = this.procedimentoService.count();
			return new ResponseEntity<>(counter, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
	}
}
