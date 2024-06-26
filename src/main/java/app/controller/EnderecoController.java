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
import org.springframework.security.core.Authentication;

import app.entity.Endereco;
import app.service.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
@CrossOrigin("*")
public class EnderecoController {
	@Autowired
    private EnderecoService enderecoService;

	/*
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @PostMapping("/save")
    public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) {
        try {
        	String userRoles = authentication.getAuthorities().toString();
            System.out.println("User roles: " + userRoles);
            
            Endereco enderecoSalvo = enderecoService.save(endereco);
            return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    */
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/save")
    public ResponseEntity<Endereco> save(@RequestBody Endereco endereco, Authentication authentication) {
        try {
            // Log the roles of the current user
            String userRoles = authentication.getAuthorities().toString();
            System.out.println("User roles: " + userRoles);

            Endereco enderecoSalvo = enderecoService.save(endereco);
            return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping("/buscar/{cep}")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable String cep) {
        try {
            Endereco endereco = enderecoService.buscarEnderecoPorCep(cep);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Endereco endereco, @PathVariable("id") long id) {
		try {
			String mensagem = this.enderecoService.update(id, endereco);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar alterar o cadastro do endereco. Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			String mensagem = this.enderecoService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar deletar o cadastro do endereço Erro: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/listAll")
	public ResponseEntity<List<Endereco>> listAll() {
		try {
			List<Endereco> lista = this.enderecoService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable("id") long id) {
		try {
			Endereco endereco = this.enderecoService.findById(id);
			return new ResponseEntity<>(endereco, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
