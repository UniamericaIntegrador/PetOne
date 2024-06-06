package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import app.dto.EnderecoViaCep;
import app.entity.Endereco;
import app.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco buscarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        EnderecoViaCep response = restTemplate.getForObject(url, EnderecoViaCep.class);

        if (response != null && response.getCep() != null) {
            Endereco endereco = new Endereco();
            endereco.setCep(response.getCep());
            endereco.setLogradouro(response.getLogradouro());
            endereco.setBairro(response.getBairro());
            endereco.setCidade(response.getLocalidade());
            endereco.setEstado(response.getUf());
            return endereco;
        } else {
            throw new RuntimeException("CEP não encontrado");
        }
    }

    public Endereco save(Endereco endereco) {
        Endereco enderecoCompleto = buscarEnderecoPorCep(endereco.getCep());
        enderecoCompleto.setNumero(endereco.getNumero());
        enderecoCompleto.setComplemento(endereco.getComplemento());
        return enderecoRepository.save(enderecoCompleto);
    }
    
    public String update(long id, Endereco endereco) {
    	endereco.setId(id);
    	
    	Endereco enderecoCompleto = buscarEnderecoPorCep(endereco.getCep());
		this.enderecoRepository.save(enderecoCompleto);
		return "Endereço alterado com sucesso!";
	}
	
	public String delete(long id) {
		if(id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.enderecoRepository.deleteById(id);
			return "Endereço deletado com sucesso!";
		}
	}
	
	public List<Endereco>listAll(){
		return this.enderecoRepository.findAll();
	}
	
	public Endereco findById(long id) {
		Endereco endereco = this.enderecoRepository.findById(id).get();
		return endereco;
	}


}
