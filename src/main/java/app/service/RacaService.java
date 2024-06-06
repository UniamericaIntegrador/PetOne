package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Raca;
import app.repository.RacaRepository;

@Service
public class RacaService {
	@Autowired
	private RacaRepository racaRepository;
	
	public String save(Raca raca) {
		this.racaRepository.save(raca);
		return "Raça " + raca.getNome() + " cadastrada com sucesso!";
	}
	
	
	public String update(long id, Raca raca) {
		raca.setId(id);
		this.racaRepository.save(raca);
		return "Cadastro de raça " + raca.getNome() + " alterado com sucesso!";
	}
	
	
	public String delete(long id) {
		if (id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.racaRepository.deleteById(id);
			return "Cadastro da raça deletado com sucesso!";
		}
	}
	
	public List<Raca>listAll(){
		return this.racaRepository.findAll();
	}
	
	public Raca findById(long id) {
		Raca raca = this.racaRepository.findById(id).get();
		return raca;
	}

}
