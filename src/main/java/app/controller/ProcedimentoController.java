package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import app.entity.Veterinario;
import app.service.ProcedimentoService;

@RestController

@RequestMapping("/api/procedimento")
public class ProcedimentoController {
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@PostMapping("/save")
	public ResponseEntity<String>save(@RequestBody Procedimento procedimento){
		try {
			
			String mensagem = this.procedimentoService.save(procedimento);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@RequestBody Procedimento procedimento, @PathVariable long id){
		try {
			String mensagem = this.procedimentoService.update(id, procedimento);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>delete(@PathVariable long id){
		try {
			String mensagem = this.procedimentoService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Procedimento>>listAll(){
		try {
			List<Procedimento>lista = this.procedimentoService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Procedimento>findById(@PathVariable long id){
		try {
			Procedimento procedimento = this.procedimentoService.findById(id);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByData/")
	public ResponseEntity<List<Procedimento>>findAllByDataBetween(@RequestParam String data1, String data2){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findAllByDataBetween(data1, data2);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByResultado/{resultado}")
	public ResponseEntity<List<Procedimento>>findByResultado(@PathVariable String resultado){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findByResultado(resultado);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByDiagnostico/{diagnostico}")
	public ResponseEntity<List<Procedimento>>findByDiagnostico(@PathVariable String diagnostico){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findByDiagnostico(diagnostico);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByVeterinario/{veterinario}")
	public ResponseEntity<List<Procedimento>>findByVeterinario(@PathVariable Veterinario veterinario){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findByVeterinario(veterinario);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByVeterinarioNome/{nome}")
	public ResponseEntity<List<Procedimento>>findByVeterinarioNome(@PathVariable String nome){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findByVeterinarioNome(nome);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByVeterinarioNome/{crmv}")
	public ResponseEntity<List<Procedimento>>findByVetarinarioCrmv(@PathVariable String crmv){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findByVetarinarioCrmv(crmv);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByNomeProcedimento/{crmv}")
	public ResponseEntity<List<Procedimento>>findByNomeProcedimento(@PathVariable String nomeProcedimento){
		try {
			List<Procedimento> procedimento = this.procedimentoService.findByNomeProcedimento(nomeProcedimento);
			return new ResponseEntity<>(procedimento, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
