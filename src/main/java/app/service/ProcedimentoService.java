package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Procedimento;
import app.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	public String save(Procedimento procedimento) {
		this.procedimentoRepository.save(procedimento);
		return "Procedimento " + procedimento.getNomeProcedimento() + " cadastrado com sucesso!";
	}

	public String update(long id, Procedimento procedimento) {
		procedimento.setId(id);
		this.procedimentoRepository.save(procedimento);
		return "Cadastro do procedimento " + procedimento.getNomeProcedimento() + " alterado com sucesso!";
	}

	public String delete(long id) {
		if (id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
			this.procedimentoRepository.deleteById(id);
			return "Cadastro do procedimento deletado com sucesso!";
		}
	}

	public List<Procedimento> listAll() {
		return this.procedimentoRepository.findAll();
	}

	public Procedimento findById(long id) {
		Procedimento procedimento = this.procedimentoRepository.findById(id).get();
		return procedimento;
	}

}
