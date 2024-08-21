package app.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	@Autowired
	private LogsService logsService;

	public String save(Procedimento procedimento, String email) {
		if (procedimento == null)
			throw new RuntimeException("Procedimento Invalido!");
		/*
		 * if (procedimento.getAgendamento() != null) { Date data =
		 * this.formatadordeData(procedimento.getAgendamento().toString());
		 * procedimento.setAgendamento(data); }
		 */
		this.procedimentoRepository.save(procedimento);
		this.logsService.Created("procedimento", procedimento.getNomeProcedimento(), email);
		return "Procedimento " + procedimento.getNomeProcedimento() + " cadastrado com sucesso!";
	}

	public String update(long id, Procedimento procedimento, String email) {
		if (Objects.isNull(id) || id < 0) {
			throw new RuntimeException("id invalido!");
		} else {
			procedimento.setId(id);
			this.logsService.Updated("procedimento", procedimento.getNomeProcedimento(), email);
			this.procedimentoRepository.save(procedimento);
			return "Cadastro do procedimento " + procedimento.getNomeProcedimento() + " alterado com sucesso!";
		}
	}

	public String delete(long id, String email) {
		if (Objects.isNull(id) || id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
			this.logsService.Deleted("veterinario", this.procedimentoRepository.findById(id).get().getNomeProcedimento(), email);
			this.procedimentoRepository.deleteById(id);
			return "Cadastro do procedimento deletado com sucesso!";
		}
	}

	public List<Procedimento> listAll() {
		return this.procedimentoRepository.findAll();
	}

	public Procedimento findById(long id) {
		if (Objects.isNull(id) || id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
			Procedimento procedimento = this.procedimentoRepository.findById(id).get();
			return procedimento;
		}
	}

	public List<Procedimento> findAllByDataBetween(String data, String data2) throws ParseException {
		if (data == null) {
			throw new RuntimeException("inválido");
		} else {
			return this.procedimentoRepository.findAllByDataBetween(formatadordeData(data), formatadordeData(data2));
		}
	}

	public List<Procedimento> findByResultado(String resultado) {
		if (resultado == null) {
			throw new RuntimeException("inválido");
		} else {
			return this.procedimentoRepository.findByResultado(resultado);
		}
	}

	public List<Procedimento> findByDiagnostico(String diagnostico) {
		if (diagnostico == null) {
			throw new RuntimeException("inválido");
		} else {
			return this.procedimentoRepository.findByDiagnostico(diagnostico);
		}
	}

	public List<Procedimento> findByVeterinario(long id) {
		Veterinario veterinario = new Veterinario();
		veterinario.setId(id);

		return this.procedimentoRepository.findByVeterinario(veterinario);
	}

	public List<Procedimento> findByVeterinarioNome(String nome) {
		if (nome == null) {
			throw new RuntimeException("Nome inválido. O Nome deve ser valido");
		} else {
			return this.procedimentoRepository.findByVeterinarioNome(nome);
		}
	}

	public List<Procedimento> findByVetarinarioCrmv(String crmv) {
		if (crmv == null) {
			throw new RuntimeException("CRMV inválido. O CRMV deve ser valido");
		} else {
			return this.procedimentoRepository.findByVeterinarioCrmv(crmv);
		}
	}

	public List<Procedimento> findByNomeProcedimento(String nomeProcedimento) {
		if (nomeProcedimento == null) {
			throw new RuntimeException("Procedimento inválido. O Procedimento deve ser valido");
		} else {
			return this.procedimentoRepository.buscarPorNomeProcedimento(nomeProcedimento);
		}
	}

	public LocalDate formatadordeData(String data) {
		data = data.replaceAll(" [^0-9/]", "");
		DateTimeFormatter formatadorEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatadorSaida = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataFormatada = LocalDate.parse(data, formatadorEntrada);
		String dataSaida = dataFormatada.format(formatadorSaida);
		return LocalDate.parse(dataSaida, formatadorSaida);
	}
	
	public long count() {
		return this.procedimentoRepository.count();
	}
}
