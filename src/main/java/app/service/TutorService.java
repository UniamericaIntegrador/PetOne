package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import app.entity.Tutor;
import app.repository.TutorRepository;

@Service
public class TutorService {
	@Autowired
	private TutorRepository tutorRepository;

	/*
	public String save(Tutor tutor) {
		this.verificaIdadeTutor(tutor);
		this.tutorRepository.save(tutor);
		return "Tutor(a) " + tutor.getNome() + " cadastrado com sucesso!";
	}
	*/

	

	public String update(long id, Tutor tutor) {
		tutor.setId(id);
		this.verificaIdadeTutor(tutor);
		this.tutorRepository.save(tutor);
		return "Cadastro do tutor(a) " + tutor.getNome() + " alterado com sucesso!";
	}
	
	

	public String delete(long id) {
		if (id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
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
}