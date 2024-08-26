package app.service;

import app.dto.TutorDTO;
import app.entity.Tutor;
import app.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {
	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private LogsService logsService;

	/*
	public String save(Tutor tutor) {
		this.verificaIdadeTutor(tutor);
		this.tutorRepository.save(tutor);
		return "Tutor(a) " + tutor.getNome() + " cadastrado com sucesso!";
	}
	*/

	

	public String update(long id, Tutor tutor, String email) {
		tutor.setId(id);
		this.verificaIdadeTutor(tutor);
		this.tutorRepository.save(tutor);
		this.logsService.Updated("tutores", tutor.getNome(), email);
		return "Cadastro do tutor(a) " + tutor.getNome() + " alterado com sucesso!";
	}
	
	

	public String delete(long id, String email) {
		if (id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
			this.logsService.Deleted("tutores", this.tutorRepository.findById(id).get().getNome(), email);
			this.tutorRepository.deleteById(id);
			return "Cadastro do tutor(a) deletado com sucesso!";
		}
	}

	public List<Tutor> listAll() {
		return this.tutorRepository.findAll();
	}

	public Tutor findById(long id) {
		Tutor tutor = this.tutorRepository.findById(id).get();
		return tutor;
	}

	public List<Tutor> findByNome(String nome) {
		return this.tutorRepository.findByNome(nome);
	}

	public List<Tutor> findByCpf(String cpf) {
		return this.tutorRepository.findByCpf(cpf);
	}

	/*
	public List<Tutor> findByEndereco(String endereco) {
		return this.tutorRepository.findByEndereco(endereco);
	}
	*/

	public Tutor findByEmail(String email) {
		return this.tutorRepository.findByEmail(email);
	}

	public List<Tutor> findByPacienteNome(String nome) {
		return this.tutorRepository.findByPacienteNome(nome);
	}

	// NOME QUE CONTENHA
	public List<Tutor> findByTrechoNome(String nome) {
		return this.tutorRepository.findByTrechoNome(nome);
	}

	// CPF QUE CONTENHA
	public List<Tutor> findByTrechoCpf(String cpf) {
		return this.tutorRepository.findByTrechoCpf(cpf);
	}
	//VERIFICAÇÃO DE IDADE DO TUTOR
	public Tutor verificaIdadeTutor(Tutor tutor) {
		if (tutor.getIdade() < 18) {
			throw new RuntimeException();
		}
		return tutor;
	}
	
	public long count() {
		return this.tutorRepository.count();
	}

	public TutorDTO active(String username) {
		Tutor tutor = this.tutorRepository.findByEmail(username);
		TutorDTO tutorformatado = new TutorDTO();
		tutorformatado.setId(tutor.getId());
		tutorformatado.setEmail(tutor.getEmail());
		tutorformatado.setRole(tutor.getRole());
		tutorformatado.setEndereco(tutor.getEndereco());
		tutorformatado.setNome(tutor.getNome());
		System.out.println(tutorformatado);
		return tutorformatado;
	}
}