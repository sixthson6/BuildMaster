package dev.service;

import dev.dto.ProjectDTO;
import dev.model.Developer;
import dev.model.Project;
import dev.model.Task;
import dev.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        return projectRepository.findById(id).map(p -> {
            p.setName(project.getName());
            p.setDescription(project.getDescription());
            p.setDeadline(project.getDeadline());
            p.setStatus(project.getStatus());
            return projectRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .deadline(project.getDeadline())
                .status(project.getStatus().name())  // if enum
                .taskIds(project.getTasks().stream()
                        .map(Task::getId)
                        .collect(Collectors.toSet()))
                .developerIds(project.getDevelopers().stream()
                        .map(Developer::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

}

