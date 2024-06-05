package ru.patseev.recordsserver.repositoryies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.patseev.recordsserver.domain.Tool;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool,String> {
@Query("from Tool T where T.code like %:code%")
    List<Tool> findAllByCodeLike(String code);

    Tool findByCode(String code);

    @Query("delete from Tool T where t.code = :code")
    void deleteByCode(String code);
}
