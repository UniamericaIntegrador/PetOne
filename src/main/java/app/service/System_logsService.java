package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.entity.System_logs;
import app.repository.System_logsRepository;

public class System_logsService {
	@Autowired
	private System_logsRepository system_logsRepository;
	public List<System_logs>listAll(){
		return this.system_logsRepository.findAll();
	}

}
