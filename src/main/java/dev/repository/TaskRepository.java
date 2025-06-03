package dev.repository;

import dev.model.Task;
import dev.model.Developer;
import dev.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
    List<Task> findByDeveloper(Developer developer);
}
