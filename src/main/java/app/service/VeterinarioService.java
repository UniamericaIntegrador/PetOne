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
	
	public String update(long id, Veterinario veterinario) {
		veterinario.setId(id);
		this.veterinarioRepository.save(veterinario);
		return "Cadastro do veterinário(a) " +veterinario.getNome() + " alterado com sucesso!";
	}
	
	public String delete(long id) {
		if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.veterinarioRepository.deleteById(id);
			return "Cadastro do veterinário(a) deletado com sucesso!";
		}
	}
	
	public List<Veterinario>listAll(){
		return this.veterinarioRepository.findAll();
	}
	
	public Veterinario findById(long id) {
		Veterinario veterinario = this.veterinarioRepository.findById(id).get();
		return veterinario;
	}
=======
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    
    public String save(Veterinario veterinario) {
        this.veterinarioRepository.save(veterinario);
        return "Vetarinário(a) "+veterinario.getNome() + " cadastrado com sucesso!";
    }
    
    // Método para atualizar um veterinário pelo ID
    // Verificar se o ID existe antes de atualizar
    public String update(long id, Veterinario veterinario) {
        // **************************** VERIFICAR POR QUE ESTÁ MUDANDO O ID. CADA ALTERACAO ELE DA UM NOVO ID **************************** 
        // Verifica se o veterinário com o ID fornecido existe
        if (!veterinarioRepository.existsById(id)) {
            throw new RuntimeException("ID inválido. O veterinário não existe.");
        }
        veterinario.setId(id);
        this.veterinarioRepository.save(veterinario);
        return "Cadastro do veterinário(a) " +veterinario.getNome() + " alterado com sucesso!";
    }
    
    // Método para deletar um veterinário pelo ID
    public String delete(long id) {
        //fazer validaçao se o id existe ou não
        if(id < 0) {
            throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
        }else {
            this.veterinarioRepository.deleteById(id);
            return "Cadastro do veterinário(a) deletado com sucesso!";
        }
    }
    
    // Método para listar todos os veterinários
    // ver se tem como fazer uma verificação para dar mensagem de lista nula
    public List<Veterinario> listAll(){
        return this.veterinarioRepository.findAll();
    }
    
    // Método para encontrar um veterinário pelo ID
    public Veterinario findById(long id) {
        //fazer validaçao se o id existe ou não
        Veterinario veterinario = this.veterinarioRepository.findById(id).get();
        return veterinario;
    }
    
    // Método para encontrar veterinários pelo nome
    public List<Veterinario> findByNome(String nome) {
        return this.veterinarioRepository.findByNome(nome);
    }
>>>>>>> refs/remotes/origin/master

    // Método para encontrar veterinários pelo CRMV
    public Veterinario findByCrmv(String crmv) {
        return this.veterinarioRepository.findByCrmv(crmv);
    }

    // Método para encontrar veterinários pelo endereço
    public List<Veterinario> findByEndereco(String endereco) {
        return this.veterinarioRepository.findByEndereco(endereco);
    }

    // JPQL para encontrar veterinários com nome iniciando com uma determinada letra
    public List<Veterinario> findByNomeStartingWith(String letra) {
        return this.veterinarioRepository.findByNomeStartingWith(letra);
    }

    // JPQL para atualizar o endereço do veterinário pelo ID
    public void updateEnderecoById(long id, String novoEndereco) {
        // Verifica se o veterinário com o ID fornecido existe
        if (!veterinarioRepository.existsById(id)) {
            throw new RuntimeException("ID inválido. O veterinário não existe.");
        }
        this.veterinarioRepository.updateEnderecoById(id, novoEndereco);
    }
}
