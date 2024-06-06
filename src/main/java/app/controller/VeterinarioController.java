package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import app.entity.Paciente;
import app.entity.Veterinario;
import app.service.VeterinarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/veterinario")
@Validated
@CrossOrigin("*")
public class VeterinarioController {
	@Autowired
	private VeterinarioService veterinarioService;

	// Método: POST
	// URL: http://localhost:8080/api/veterinario/save
	// Endpoint para salvar um novo veterinário (CRUD Básico)
	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Veterinario veterinario) {
		try {
			String mensagem = this.veterinarioService.save(veterinario);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// Método: PUT
	// URL: http://localhost:8080/api/veterinario/update/{id}
	// Endpoint para atualizar um veterinário existente pelo ID (CRUD Básico)
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@Valid @RequestBody Veterinario veterinario, @PathVariable("id") long id){
		try {
			String mensagem = this.veterinarioService.update(id, veterinario);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro. Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Método: DELETE
	// URL: http://localhost:8080/api/veterinario/delete/{id}
	// Endpoint para deletar um veterinário pelo ID (CRUD Básico)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			String mensagem = this.veterinarioService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// Método: GET
	// URL: http://localhost:8080/api/veterinario/listAll
	// Endpoint para listar todos os veterinários (CRUD Básico)
	@GetMapping("/listAll")
	public ResponseEntity<List<Veterinario>> listAll() {
		try {
			List<Veterinario> lista = this.veterinarioService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Método: GET
	// URL: http://localhost:8080/api/veterinario/findById/{id}
	// Endpoint para encontrar um veterinário pelo ID (CRUD Básico)
	@GetMapping("/findById/{id}")
	public ResponseEntity<Veterinario> findById(@PathVariable("id") long id) {
		try {
			Veterinario veterinario = this.veterinarioService.findById(id);
			return new ResponseEntity<>(veterinario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Método: GET
	// URL: http://localhost:8080/api/veterinario/findByNome/{nome}
	// Endpoint para encontrar veterinários pelo nome (automatico)
	@GetMapping("/findByNome/{nome}")
	public ResponseEntity<List<Veterinario>> findByNome(@PathVariable("nome") String nome) {
		try {
			List<Veterinario> veterinarios = this.veterinarioService.findByNome(nome);
			return new ResponseEntity<>(veterinarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Método: GET
	// URL: http://localhost:8080/api/veterinario/findByCrmv/{crmv}
	// Endpoint para encontrar veterinário pelo CRMV (automatico)
	@GetMapping("/findByCrmv/{crmv}")
	public ResponseEntity<Veterinario> findByCrmv(@PathVariable("crmv") String crmv) {
		try {
			Veterinario veterinario = this.veterinarioService.findByCrmv(crmv);
			return new ResponseEntity<>(veterinario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	// Método: GET
	// URL: http://localhost:8080/api/veterinario/findByEndereco/{endereco}
	// Endpoint para encontrar veterinários pelo endereço (automatico)
	@GetMapping("/findByEndereco/{endereco}")
	public ResponseEntity<List<Veterinario>> findByEndereco(@PathVariable("endereco") String endereco) {
		try {
			List<Veterinario> veterinarios = this.veterinarioService.findByEndereco(endereco);
			return new ResponseEntity<>(veterinarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	*/

	// Método: GET
	// URL: http://localhost:8080/api/veterinario/findByNomeStartingWith/{letra}
	// Endpoint para encontrar veterinários com nome iniciando com uma determinada
	// letra (JPQL)
	@GetMapping("/findByNomeStartingWith/{letra}")

	public ResponseEntity<List<Veterinario>> findByNomeStartingWith(@PathVariable("letra") String letra) {
		try {
			List<Veterinario> veterinarios = this.veterinarioService.findByNomeStartingWith(letra);
			return new ResponseEntity<>(veterinarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Método: PUT
	// URL:
	// http://localhost:8080/api/veterinario/updateEnderecoById/{id}/{novoEndereco}
	// Endpoint para atualizar o endereço do veterinário pelo ID (JPQL)
	@PutMapping("/updateEnderecoById/{id}/{novoEndereco}")
	public ResponseEntity<String> updateEnderecoById(@PathVariable("id") long id,
			@PathVariable("novoEndereco") String novoEndereco) {
		try {
			this.veterinarioService.updateEnderecoById(id, novoEndereco);
			return new ResponseEntity<>("Endereço do veterinário atualizado com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(
					"Algo deu errado ao tentar atualizar o endereço do veterinário. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		try {
			long counter = this.veterinarioService.count();
			return new ResponseEntity<>(counter, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
	}
}
