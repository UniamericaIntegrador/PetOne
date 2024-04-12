package app.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Procedimento;
import app.entity.Veterinario;
import app.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	public String save(Procedimento procedimento) {
		if(procedimento == null)
			throw new RuntimeException();
		this.procedimentoRepository.save(procedimento);
		return "Procedimento "+procedimento.getNomeProcedimento() + " cadastrado com sucesso!";
	}
	
	
	//fazer validaçao se o id existe ou não
	public String update(long id, Procedimento procedimento) {
		if(Objects.isNull(id) || id < 0) {
			throw new RuntimeException("id invalido!");
		}else {		
		procedimento.setId(id);
		this.procedimentoRepository.save(procedimento);
		return "Cadastro do procedimento " +procedimento.getNomeProcedimento() + " alterado com sucesso!";
		}
	}
	
	//fazer validaçao se o id existe ou não
	public String delete(long id) {
		if(Objects.isNull(id) || id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			this.procedimentoRepository.deleteById(id);
			return "Cadastro do procedimento deletado com sucesso!";
		}
	}
	
	//ver se tem como fazer uma verificação para dar mensagem de lista nula
	public List<Procedimento>listAll(){
		return this.procedimentoRepository.findAll();
	}

	public Procedimento findById(long id) {
		if(Objects.isNull(id) || id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			Procedimento procedimento = this.procedimentoRepository.findById(id).get();
			return procedimento;
		}
	}
	
	public List<Procedimento> findByData(String data){
		if(data == null) {
			throw new RuntimeException("inválido");
		}else {
			return this.procedimentoRepository.findByData(data);
		}
	}
	
	public List<Procedimento> findByResultado(String resultado){
		if(resultado == null) {
			throw new RuntimeException("inválido");
		}else {
			return this.procedimentoRepository.findByResultado(resultado);
		}
	}
	
	public List<Procedimento> findByDiagnostico(String diagnostico){
		if(diagnostico == null) {
			throw new RuntimeException("inválido");
		}else {
			return this.procedimentoRepository.findByDiagnostico(diagnostico);
		}
	}
	
	public List<Procedimento> findByVeterinario(Veterinario veterinario){
		if(veterinario == null) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		}else {
			return this.procedimentoRepository.findByVeterinario(veterinario);
		}
	}
	
	public List<Procedimento> findByVeterinarioNome(String nome){
		if(nome == null) {
			throw new RuntimeException("Nome inválido. O Nome deve ser valido");
		}else {
			return this.procedimentoRepository.findByVeterinarioNome(nome);
		}
	}
	
	public List<Procedimento> findByVetarinarioCrmv(String crmv){
		if(crmv == null) {
			throw new RuntimeException("CRMV inválido. O CRMV deve ser valido");
		}else {
			return this.procedimentoRepository.findByVeterinarioNome(crmv);
		}
	}

	public List<Procedimento> findByNomeProcedimento(String nomeProcedimento){
		if(nomeProcedimento == null) {
			throw new RuntimeException("Procedimento inválido. O Procedimento deve ser valido");
		}else {
			return this.procedimentoRepository.findByVeterinarioNome(nomeProcedimento);
		}
	}
}
