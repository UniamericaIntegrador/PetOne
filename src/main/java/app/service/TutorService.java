package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Tutor;
import app.repository.TutorRepository;

@Service
public class TutorService {
	@Autowired
	private TutorRepository tutorRepositry;
	
	public String save(Tutor tutor) {
		this.tutorRepositry.save(tutor);
		return "Tutor(a) "+tutor.getNome() + " cadastrado com sucesso!";
	}
	
	
	//fazer validaçao se o id existe ou não
	public String update(long id, Tutor tutor) {
		tutor.setId(id);
		this.tutorRepositry.save(tutor);
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
			this.tutorRepositry.deleteById(id);
			return "Cadastro do tutor(a) deletado com sucesso!";
		}
	}
	
	//ver se tem como fazer uma verificação para dar mensagem de lista nula
	public List<Tutor>listAll(){
		return this.tutorRepositry.findAll();
	}
	
	public Tutor findById(long id) {
		Tutor tutor = this.tutorRepositry.findById(id).get();
		return tutor;
	}

}
