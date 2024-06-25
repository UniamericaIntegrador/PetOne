package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Especie;
import app.service.EspecieService;

@RestController

@RequestMapping("/api/especie")
@CrossOrigin("*")
public class EspecieController {
	@Autowired
	private EspecieService especieService;

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PostMapping("/save")
	public ResponseEntity<Especie> save(@RequestBody Especie especie) {
		try {
			Especie retorno = this.especieService.save(especie);
			return new ResponseEntity<>(retorno, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Especie> update(@RequestBody Especie especie, @PathVariable("id") long id) {
		try {
			Especie retorno = this.especieService.update(id, especie);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			String mensagem = this.especieService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(
					"Algo deu errado ao tentar deletar o cadastro da espécie. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/listAll")
	public ResponseEntity<List<Especie>> listAll() {
		try {
			List<Especie> lista = this.especieService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Especie> findById(@PathVariable("id") long id) {
		try {
			Especie especie = this.especieService.findById(id);
			return new ResponseEntity<>(especie, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
