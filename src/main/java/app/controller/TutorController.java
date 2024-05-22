package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import app.entity.Tutor;
import app.service.TutorService;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/tutor")
@Validated
@CrossOrigin("*")
public class TutorController {
	@Autowired
	private TutorService tutorService;
	
	@PostMapping("/save")
	public ResponseEntity<String>save(@Valid @RequestBody Tutor tutor){
		try {
			String mensagem = this.tutorService.save(tutor);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@Valid @RequestBody Tutor tutor, @PathVariable("id") long id){
		try {
			String mensagem = this.tutorService.update(id, tutor);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>delete(@PathVariable("id") long id){
		try {
			String mensagem = this.tutorService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Tutor>>listAll(){
		try {
			List<Tutor>lista = this.tutorService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Tutor>findById(@PathVariable("id") long id){
		try {
			Tutor tutor = this.tutorService.findById(id);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByNome")
	public ResponseEntity<List<Tutor>>findByNome(@RequestParam("nome") String nome){
		try {
			List<Tutor> tutor = this.tutorService.findByNome(nome);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByCpf")
	public ResponseEntity<List<Tutor>>findByCpf(@RequestParam("cpf") String cpf){
		try {
			List<Tutor> tutor = this.tutorService.findByCpf(cpf);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByEndereco")
	public ResponseEntity<List<Tutor>>findByEndereco(@RequestParam("endereco") String endereco){
		try {
			List<Tutor> tutor = this.tutorService.findByEndereco(endereco);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByPacienteNome")
	public ResponseEntity<List<Tutor>>findByPacienteNome(@RequestParam("nome") String nome){
		try {
			List<Tutor> tutor = this.tutorService.findByPacienteNome(nome);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByTrechoNome")
	public ResponseEntity<List<Tutor>>findByTrechoNome(@RequestParam("nome") String nome){
		try {
			List<Tutor> tutor = this.tutorService.findByTrechoNome(nome);
			return new ResponseEntity<>(tutor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
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
}
