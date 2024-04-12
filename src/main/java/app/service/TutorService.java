package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Tutor;
import app.repository.TutorRepository;

@Service
public class TutorService {
	@Autowired
	private TutorRepository tutorRepository;
	
	public String save(Tutor tutor) {
		this.tutorRepository.save(tutor);
		return "Tutor(a) "+tutor.getNome() + " cadastrado com sucesso!";
	}
	
	
	//fazer validaçao se o id existe ou não
	public String update(long id, Tutor tutor) {
		tutor.setId(id);
		this.tutorRepository.save(tutor);
		return "Cadastro do tutor(a) " +tutor.getNome() + " alterado com sucesso!";
		
		/*if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.tutorRepositry.save(tutor);
			return "Cadastro do tutor(a) " +tutor.getNome() + " alterado com sucesso!";
		}
		*/
	}
	
	//fazer validaçao se o id existe ou não
	public String delete(long id) {
		if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.tutorRepository.deleteById(id);
			return "Cadastro do tutor(a) deletado com sucesso!";
		}
	}
	
	//ver se tem como fazer uma verificação para dar mensagem de lista nula
	public List<Tutor>listAll(){
		return this.tutorRepository.findAll();
	}
	
	public Tutor findById(long id) {
		Tutor tutor = this.tutorRepository.findById(id).get();
		return tutor;
	}
	
	public List<Tutor> findByNome (String nome) {
		return this.tutorRepository.findByNome(nome);
	}
	
	public List<Tutor> findByCpf (String cpf){
		return this.tutorRepository.findByCpf(cpf);
	}
	
	public List<Tutor> findByEndereco (String endereco){
		return this.tutorRepository.findByEndereco(endereco);
	}
	
	public List<Tutor> findByPacienteNome (String nome){
		return this.tutorRepository.findByPacienteNome(nome);
	}
	
	// NOME QUE CONTENHA
	public List<Tutor> findByTrechoNome(String nome) {
		return this.tutorRepository.findByTrechoNome(nome);
	}
	
	// CPF QUE CONTENHA
	public List<Tutor> findByTrechoCpf(String cpf) {
		return this.tutorRepository.findByTrechoNome(cpf);
	}

}
