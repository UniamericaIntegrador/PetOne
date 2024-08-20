package app.service;

import app.entity.Logs;
import app.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;

    public List<Logs> listAll(){
        return logsRepository.findAll();
    }

}
