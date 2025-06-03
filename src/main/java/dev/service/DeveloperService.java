package dev.service;

import dev.dto.DeveloperDTO;
import dev.model.Developer;
import dev.model.Task;
import dev.model.Project;
import dev.repository.DeveloperRepository;
import dev.repository.TaskRepository;
import dev.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public Developer createDeveloper(Developer developer) {
        // Fetch managed Task entities from DB using their IDs
        Set<Task> managedTasks = new HashSet<>();
        if (developer.getTasks() != null) {
            for (Task task : developer.getTasks()) {
                Task managedTask = taskRepository.findById(task.getId())
                        .orElseThrow(() -> new RuntimeException("Task not found with id: " + task.getId()));
                managedTasks.add(managedTask);
            }
        }

        // Fetch managed Project entities from DB using their IDs
        Set<Project> managedProjects = new HashSet<>();
        if (developer.getProjects() != null) {
            for (Project project : developer.getProjects()) {
                Project managedProject = projectRepository.findById(project.getId())
                        .orElseThrow(() -> new RuntimeException("Project not found with id: " + project.getId()));
                managedProjects.add(managedProject);
            }
        }

        developer.setTasks(managedTasks);
        developer.setProjects(managedProjects);

        return developerRepository.save(developer);
    }

    public Developer updateDeveloper(Long id, Developer updated) {
        return developerRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            existing.setSkills(updated.getSkills());

            // Update tasks with managed entities
            Set<Task> managedTasks = new HashSet<>();
            if (updated.getTasks() != null) {
                for (Task task : updated.getTasks()) {
                    Task managedTask = taskRepository.findById(task.getId())
                            .orElseThrow(() -> new RuntimeException("Task not found with id: " + task.getId()));
                    managedTasks.add(managedTask);
                }
            }
            existing.setTasks(managedTasks);

            // Update projects with managed entities
            Set<Project> managedProjects = new HashSet<>();
            if (updated.getProjects() != null) {
                for (Project project : updated.getProjects()) {
                    Project managedProject = projectRepository.findById(project.getId())
                            .orElseThrow(() -> new RuntimeException("Project not found with id: " + project.getId()));
                    managedProjects.add(managedProject);
                }
            }
            existing.setProjects(managedProjects);

            return developerRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Developer not found"));
    }

    public void deleteDeveloper(Long id) {
        developerRepository.deleteById(id);
    }

    public Developer getDeveloper(Long id) {
        return developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    public DeveloperDTO toDTO(Developer developer) {
        return DeveloperDTO.builder()
                .id(developer.getId())
                .name(developer.getName())
                .email(developer.getEmail())
                .skills(developer.getSkills())
                .projectIds(developer.getProjects() != null
                        ? developer.getProjects().stream().map(Project::getId).collect(Collectors.toSet())
                        : Collections.emptySet())
                .build();
    }
}
