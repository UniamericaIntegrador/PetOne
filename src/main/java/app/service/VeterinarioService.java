package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Veterinario;
import app.repository.VeterinarioRepository;

@Service
public class VeterinarioService {
	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	public String save(Veterinario veterinario) {
		this.veterinarioRepository.save(veterinario);
		return "Vetarinário(a) "+veterinario.getNome() + " cadastrado com sucesso!";
	}
	
	// **************************** VERIFICAR POR QUE ESTÁ MUDANDO O ID. CADA ALTERACAO ELE DA UM NOVO ID **************************** 
	//fazer validaçao se o id existe ou não
	public String update(long id, Veterinario veterinario) {
		veterinario.setId(id);
		this.veterinarioRepository.save(veterinario);
		return "Cadastro do veterinário(a) " +veterinario.getNome() + " alterado com sucesso!";
		
		/*if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.veterinarioRepository.save(veterinario);
			return "Cadastro do veterinário(a) " +veterinario.getNome() + " alterado com sucesso!";
		}
		*/
	}
	
	//fazer validaçao se o id existe ou não
	public String delete(long id) {
		if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.veterinarioRepository.deleteById(id);
			return "Cadastro do veterinário(a) deletado com sucesso!";
		}
	}
	
	//ver se tem como fazer uma verificação para dar mensagem de lista nula
	public List<Veterinario>listAll(){
		return this.veterinarioRepository.findAll();
	}
	
	public Veterinario findById(long id) {
		Veterinario veterinario = this.veterinarioRepository.findById(id).get();
		return veterinario;
	}

}
