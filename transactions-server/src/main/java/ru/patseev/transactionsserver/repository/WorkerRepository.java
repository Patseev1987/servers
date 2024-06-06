package ru.patseev.transactionsserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.patseev.transactionsserver.domain.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
