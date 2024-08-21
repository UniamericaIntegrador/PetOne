package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Especie;
import app.repository.EspecieRepository;

@Service
public class EspecieService {
	@Autowired
	private EspecieRepository especieRepository;

	@Autowired
	private LogsService logsService;
	
	public Especie save(Especie especie) {
		return this.especieRepository.save(especie);
		//return "Espécie " + especie.getNome() + " cadastrada com sucesso!";
	}
	
	public Especie update(long id, Especie especie) {
		especie.setId(id);
		return this.especieRepository.save(especie);
		
		//return "Cadastro da espécie " + especie.getNome() + " alterado com sucesso!";
	}
	
	public String delete(long id) {
		if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.especieRepository.deleteById(id);
			return "Cadastro da espécie deletado com sucesso!";
		}
	}
	
	public List<Especie>listAll(){
		return this.especieRepository.findAll();
	}
	
	public Especie findById(long id) {
		Especie especie = this.especieRepository.findById(id).get();
		return especie;
	}

}
