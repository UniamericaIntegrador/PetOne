package app.service;

import app.dto.AgendamentoDTO;
import app.entity.Agendamento;
import app.entity.Veterinario;
import app.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class AgendamentoService {
	@Autowired
	private AgendamentoRepository AgendamentoRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private VeterinarioService veterinarioService;

	@Autowired
	private LogsService logsService;

	public String save(AgendamentoDTO Agendamento, String email) {
		if (Agendamento == null)
			throw new RuntimeException("Agendamento Invalido!");
		this.AgendamentoRepository.save(this.DTOtoEntity(Agendamento, 0));
		this.logsService.Created("Agendamento", Agendamento.getNome(), email);
		return "Agendamento " + Agendamento.getNome() + " cadastrado com sucesso!";
	}

	public String update(long id, AgendamentoDTO Agendamento, String email) {
		if (Objects.isNull(id) || id < 0) {
			throw new RuntimeException("id invalido!");
		} else {
			Agendamento.setId(id);
			this.logsService.Updated("Agendamento", Agendamento.getNome(), email);
			this.AgendamentoRepository.save(this.DTOtoEntity(Agendamento, 1));
			return "Cadastro do Agendamento " + Agendamento.getNome() + " alterado com sucesso!";
		}
	}

	public String delete(long id, String email) {
		if (Objects.isNull(id) || id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
			this.logsService.Deleted("Agendamento", this.AgendamentoRepository.findById(id).get().getNome(), email);
			this.AgendamentoRepository.deleteById(id);
			return "Cadastro do Agendamento deletado com sucesso!";
		}
	}

	public List<AgendamentoDTO> listAll() {
		return this.EntitytoDTO(this.AgendamentoRepository.findAll());
	}

	public List<Agendamento>findByPaciente(Long id) {
		System.out.println("CHEGOU AQUI2");
		return this.AgendamentoRepository.findByPacienteId(id);
	}

	public Agendamento findById(long id) {
		if (Objects.isNull(id) || id < 0) {
			throw new RuntimeException("ID inválido. O ID deve ser maior que 0.");
		} else {
			Agendamento Agendamento = this.AgendamentoRepository.findById(id).get();
			return Agendamento;
		}
	}

	public List<Agendamento> findAllByDataBetween(String data, String data2) throws ParseException {
		if (data == null) {
			throw new RuntimeException("inválido");
		} else {
			return this.AgendamentoRepository.findAllByDataBetween(formatadordeData(data), formatadordeData(data2));
		}
	}

	public List<Agendamento> findByResultado(String resultado) {
		if (resultado == null) {
			throw new RuntimeException("inválido");
		} else {
			return this.AgendamentoRepository.findByResultado(resultado);
		}
	}

	public List<Agendamento> findByDiagnostico(String diagnostico) {
		if (diagnostico == null) {
			throw new RuntimeException("inválido");
		} else {
			return this.AgendamentoRepository.findByDiagnostico(diagnostico);
		}
	}

	public List<Agendamento> findByVeterinario(long id) {
		Veterinario veterinario = new Veterinario();
		veterinario.setId(id);

		return this.AgendamentoRepository.findByVeterinario(veterinario);
	}

	public List<Agendamento> findByVeterinarioNome(String nome) {
		if (nome == null) {
			throw new RuntimeException("Nome inválido. O Nome deve ser valido");
		} else {
			return this.AgendamentoRepository.findByVeterinarioNome(nome);
		}
	}

	public List<Agendamento> findByVetarinarioCrmv(String crmv) {
		if (crmv == null) {
			throw new RuntimeException("CRMV inválido. O CRMV deve ser valido");
		} else {
			return this.AgendamentoRepository.findByVeterinarioCrmv(crmv);
		}
	}

	public List<Agendamento> findByNomeAgendamento(String nomeAgendamento) {
		if (nomeAgendamento == null) {
			throw new RuntimeException("Agendamento inválido. O Agendamento deve ser valido");
		} else {
			return this.AgendamentoRepository.buscarPorNomeAgendamento(nomeAgendamento);
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
		return this.AgendamentoRepository.count();
	}

	private List<AgendamentoDTO> EntitytoDTO(List<Agendamento> lista){
		List<AgendamentoDTO> listaFormatada = List.of();

		for(Agendamento agendamento: lista){
			listaFormatada.add(new AgendamentoDTO(
					agendamento.getId(),
					agendamento.getData(),
					agendamento.getNome(),
					agendamento.getResultado(),
					agendamento.getDiagnostico(),
					agendamento.getVeterinario().getId(),
					agendamento.getPaciente().getId()
			));
		}

		return listaFormatada;
	}

	private Agendamento DTOtoEntity(AgendamentoDTO Agendamento, int opc){
		if(opc == 0) {
			Agendamento formatedAgendamento = new Agendamento();
			formatedAgendamento.setNome(Agendamento.getNome());
			formatedAgendamento.setData(Agendamento.getData());
			formatedAgendamento.setDiagnostico(Agendamento.getDiagnostico());
			formatedAgendamento.setResultado(Agendamento.getResultado());
			formatedAgendamento.setPaciente(this.pacienteService.findById(Agendamento.getPaciente_id()));
			formatedAgendamento.setVeterinario(this.veterinarioService.findById(Agendamento.getVeterinario()));
			return formatedAgendamento;
		}else {
			Agendamento formatedAgendamento = new Agendamento();
			formatedAgendamento.setId(Agendamento.getId());
			formatedAgendamento.setNome(Agendamento.getNome());
			formatedAgendamento.setData(Agendamento.getData());
			formatedAgendamento.setDiagnostico(Agendamento.getDiagnostico());
			formatedAgendamento.setResultado(Agendamento.getResultado());
			formatedAgendamento.setPaciente(this.pacienteService.findById(Agendamento.getPaciente_id()));
			formatedAgendamento.setVeterinario(this.veterinarioService.findById(Agendamento.getVeterinario()));
			return formatedAgendamento;
		}
	}
}
