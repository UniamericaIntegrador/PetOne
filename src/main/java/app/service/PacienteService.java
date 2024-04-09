package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Paciente;
import app.repository.PacienteRepository;

@Service
public class PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public String save(Paciente paciente) {
		this.pacienteRepository.save(paciente);
		return "Paciente "+paciente.getNome() + " cadastrado com sucesso!";
	}
	
	
	//fazer validaçao se o id existe ou não
	public String update(long id, Paciente paciente) {
		paciente.setId(id);
		this.pacienteRepository.save(paciente);
		return "Cadastro do paciente " +paciente.getNome() + " alterado com sucesso!";
	}
	
	//fazer validaçao se o id existe ou não
	public String delete(long id) {
		if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.pacienteRepository.deleteById(id);
			return "Cadastro do paciente deletado com sucesso!";
		}
	}
	
	//ver se tem como fazer uma verificação para dar mensagem de lista nula
	public List<Paciente>listAll(){
		return this.pacienteRepository.findAll();
	}
	
	public Paciente findById(long id) {
		Paciente paciente = this.pacienteRepository.findById(id).get();
		return paciente;
	}

}
