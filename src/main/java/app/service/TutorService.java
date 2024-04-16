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
	
	public String update(long id, Tutor tutor) {
		tutor.setId(id);
		this.tutorRepository.save(tutor);
		return "Cadastro do tutor(a) " +tutor.getNome() + " alterado com sucesso!";
	}
	
	public String delete(long id) {
		if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.tutorRepository.deleteById(id);
			return "Cadastro do tutor(a) deletado com sucesso!";
		}
	}
	
	public List<Tutor>listAll(){
		return this.tutorRepository.findAll();
	}
	
	public Tutor findById(long id) {
		Tutor tutor = this.tutorRepository.findById(id).get();
		return tutor;
	}

}
