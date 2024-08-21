package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Paciente;
import app.entity.Raca;
import app.repository.EspecieRepository;
import app.repository.PacienteRepository;
import app.repository.RacaRepository;

@Service
public class PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private LogsService logsService;

	/*
	@Autowired
	private RacaRepository racaRepository;
	
	@Autowired
	private EspecieRepository especieRepository;
	*/
	public String save(Paciente paciente, String email) {
		//verificarEspecie(paciente);
	
		this.pacienteRepository.save(paciente);
		this.logsService.Created("paciente", paciente.getNome(), email);
		return "Paciente " + paciente.getNome() + " cadastrado com sucesso!";
	}
	
	/*
	public String save(Paciente paciente) {
		// verificarEspecie(paciente); // 
		if (paciente.getRaca().getId() == 0) {
			Especie especie = new Especie();
			especieRepository.save(especie);

			Raca raca = new Raca();
			raca.setEspecie(especie);

			racaRepository.save(paciente.getRaca());
		}

		pacienteRepository.save(paciente);
		return "Paciente " + paciente.getNome() + " cadastrado com sucesso!";
	}
	*/
	public String update(long id, Paciente paciente, String email) {
		paciente.setId(id);
		// verificarEspecie(paciente);
		this.logsService.Updated("paciente", paciente.getNome(), email);
		this.pacienteRepository.save(paciente);
		return "Cadastro do paciente " + paciente.getNome() + " alterado com sucesso!";
	}

	public String delete(long id, String email) {
		if (id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
			this.logsService.Deleted("veterinario", this.pacienteRepository.findById(id).get().getNome(), email);
			this.pacienteRepository.deleteById(id);
			return "Cadastro do paciente deletado com sucesso!";
		}
	}

	public List<Paciente> listAll() {
		return this.pacienteRepository.findAll();
	}

	public Paciente findById(long id) {
		Paciente paciente = this.pacienteRepository.findById(id).get();
		return paciente;
	}

	public List<Paciente> findByPart(String nome) {
		return this.pacienteRepository.findByPart(nome);
	}

	/*
	 * public List<Paciente> findByRaca(String raca) { return
	 * this.pacienteRepository.findByRaca(raca); }
	 */

	/*
	 * public List<Paciente> findByEspecie(String nome) { return
	 * pacienteRepository.findByRacaEspecie(nome); }
	 */

	public List<Paciente> findByAcimaAno(int ano) {
		return pacienteRepository.findByAcimaAno(ano);
	}

	public List<Paciente> findByRaca(long id) {
		Raca raca = new Raca();
		raca.setId(id);
		return this.pacienteRepository.findByRaca(raca);
	}

	/*
	public List<Paciente> findByEspecie(long id){

	public List<Paciente> findByEspecie(long id) {
		Especie especie = new Especie();
		especie.setId(id);
		return this.pacienteRepository.findByEspecie(especie);
	}
	*/
	public long count() {
		return this.pacienteRepository.count();
	}
	/*
	public Paciente racaToEspecie(Paciente paciente) {
		Raca raca = new Raca();
		raca = paciente.getRaca();
		racaRepository.save(raca);
		paciente.setRaca(raca);
		return paciente;
	}
	*/
}
