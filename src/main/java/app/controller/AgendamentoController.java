package app.controller;

import app.config.SecurityManager;
import app.dto.AgendamentoDTO;
import app.entity.Agendamento;
import app.service.AgendamentoService;
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

@RequestMapping("/api/agendamento")
@Validated
@CrossOrigin("*")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	private final SecurityManager securityManager;

	@Autowired
	public AgendamentoController(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@PostMapping("/save")
	public ResponseEntity<String>save(@Valid @RequestBody AgendamentoDTO agendamento){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.agendamentoService.save(agendamento, userDetails.getUsername());
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@Valid @RequestBody AgendamentoDTO agendamento, @PathVariable("id") long id){
		try {
			UserDetails userDetails = securityManager.getCurrentUser();
			String mensagem = this.agendamentoService.update(id, agendamento, userDetails.getUsername());
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
			String mensagem = this.agendamentoService.delete(id, userDetails.getUsername());
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@GetMapping("/findall")
	public ResponseEntity<List<AgendamentoDTO>>listAll(){
		try {
			List<AgendamentoDTO>lista = this.agendamentoService.listAll();
			System.out.println(lista);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/listbypaciente/{id}")
	public ResponseEntity<List<Agendamento>>findByPaciente(@PathVariable Long id){
		try {
			System.out.println("Chegou Aqui!");
			List<Agendamento> lista = this.agendamentoService.findByPaciente(id);
			System.out.println(lista);
			System.out.println("Chegou Aqui!2");
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Agendamento>findById(@PathVariable("id") long id){
		try {
			Agendamento agendamento = this.agendamentoService.findById(id);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByPeriodo/")
	public ResponseEntity<List<Agendamento>>findAllByDataBetween(@RequestParam String data1, String data2){
		try {
			List<Agendamento> agendamento = this.agendamentoService.findAllByDataBetween(data1, data2);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByResultado")
	public ResponseEntity<List<Agendamento>>findByResultado(@RequestParam("resultado") String resultado){
		try {
			List<Agendamento> agendamento = this.agendamentoService.findByResultado(resultado);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByDiagnostico")
	public ResponseEntity<List<Agendamento>>findByDiagnostico(@RequestParam("diagnostico") String diagnostico){
		try {
			List<Agendamento> agendamento = this.agendamentoService.findByDiagnostico(diagnostico);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByVeterinario")
	public ResponseEntity<List<Agendamento>>findByVeterinario(@RequestParam long id){
		try {
			List<Agendamento> agendamento = this.agendamentoService.findByVeterinario(id);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByVeterinarioNome")
	public ResponseEntity<List<Agendamento>>findByVeterinarioNome(@RequestParam("nome")  String nome){
		try {
			List<Agendamento> agendamento = this.agendamentoService.findByVeterinarioNome(nome);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByVeterinarioCrmv")
	public ResponseEntity<List<Agendamento>>findByVetarinarioCrmv(@RequestParam("crmv") String crmv){
		try {
			List<Agendamento> agendamento = this.agendamentoService.findByVetarinarioCrmv(crmv);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER') OR hasRole('USERVET')")
	@GetMapping("/findByNomeAgendamento")
	public ResponseEntity<List<Agendamento>>findByNomeAgendamento(@RequestParam("nomeAgendamento") String nomeAgendamento){
		try {
			List<Agendamento> agendamento = this.agendamentoService.findByNomeAgendamento(nomeAgendamento);
			return new ResponseEntity<>(agendamento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USERVET')")
	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		try {
			long counter = this.agendamentoService.count();
			return new ResponseEntity<>(counter, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
	}
}
