package ru.patseev.transactionsserver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.patseev.transactionsserver.domain.Worker;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
}
