package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.System_logs;
@Repository
public interface System_logsRepository extends JpaRepository<System_logs, Long> {
	

}
