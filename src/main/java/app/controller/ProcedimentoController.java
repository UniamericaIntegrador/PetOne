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
import org.springframework.web.bind.annotation.RestController;

import app.entity.Procedimento;
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

}
