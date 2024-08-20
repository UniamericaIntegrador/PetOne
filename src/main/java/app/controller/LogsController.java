package app.controller;

import app.entity.Logs;
import app.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin("*")
public class LogsController {
    @Autowired
    private LogsService logsService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/listAll")
    public ResponseEntity<List<Logs>> listAll() {
        try {
            List<Logs> list = logsService.listAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
