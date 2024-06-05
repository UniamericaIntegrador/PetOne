package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Endereco;
import app.service.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
	@Autowired
    private EnderecoService enderecoService;

    @PostMapping("/save")
    public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco) {
        try {
            Endereco enderecoSalvo = enderecoService.salvarEndereco(endereco);
            return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar/{cep}")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable String cep) {
        try {
            Endereco endereco = enderecoService.buscarEnderecoPorCep(cep);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
