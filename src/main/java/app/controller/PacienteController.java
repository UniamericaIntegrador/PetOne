package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Paciente paciente) {
		try {
			String mensagem = this.pacienteService.save(paciente);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody Paciente paciente, @PathVariable("id") long id) {
		try {
			String mensagem = this.pacienteService.update(id, paciente);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			String mensagem = this.pacienteService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Paciente>> listAll() {
		try {
			List<Paciente> lista = this.pacienteService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Paciente> findById(@PathVariable("id") long id) {
		try {
			Paciente paciente = this.pacienteService.findById(id);
			return new ResponseEntity<>(paciente, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByPart")
	public ResponseEntity<List<Paciente>> findByPart(@RequestParam String nome) {
		try {
			List<Paciente> lista = this.pacienteService.findByPart(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByRaca")
	public ResponseEntity<List<Paciente>> findByRaca(@RequestParam("raca") String raca) {
		try {
			List<Paciente> lista = this.pacienteService.findByRaca(raca);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByEspecie")
	public ResponseEntity<List<Paciente>> findByEspecie(@RequestParam("especie") String especie) {
		try {
			List<Paciente> lista = this.pacienteService.findByEspecie(especie);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByAcimaAno")
	public ResponseEntity<List<Paciente>> findByAcimaAno(@RequestParam int ano) {
		try {
			List<Paciente> lista = this.pacienteService.findByAcimaAno(ano);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
