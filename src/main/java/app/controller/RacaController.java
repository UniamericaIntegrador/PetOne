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

import app.entity.Raca;
import app.service.RacaService;

@RestController

@RequestMapping("/api/raca")
@CrossOrigin("*")
public class RacaController {
	@Autowired
	private RacaService racaService;
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PostMapping("/save")
	public ResponseEntity<Raca>save(@RequestBody Raca raca){
		try {
			Raca retorno = this.racaService.save(raca);
			return new ResponseEntity<>(retorno, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Raca> update(@RequestBody Raca raca, @PathVariable("id") long id) {
		try {
			Raca mensagem = this.racaService.update(id, raca);
			return new ResponseEntity<Raca>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			String mensagem = this.racaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro da raça. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/listAll")
	public ResponseEntity<List<Raca>> listAll() {
		try {
			List<Raca> lista = this.racaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Raca> findById(@PathVariable("id") long id) {
		try {
			Raca raca = this.racaService.findById(id);
			return new ResponseEntity<>(raca, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
