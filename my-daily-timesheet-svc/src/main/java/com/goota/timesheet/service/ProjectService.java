package com.goota.timesheet.service;

import com.goota.timesheet.dto.ProjectRequest;
import com.goota.timesheet.dto.ProjectResponse;
import com.goota.timesheet.entity.Project;
import com.goota.timesheet.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(p -> new ProjectResponse(p.getId(), p.getProjectBusinessId(), p.getProjectName()))
                .collect(Collectors.toList());
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return new ProjectResponse(project.getId(), project.getProjectBusinessId(), project.getProjectName());
    }

    public ProjectResponse createProject(ProjectRequest request) {
        Project project = Project.builder()
                .projectBusinessId(request.getProjectBusinessId())
                .projectName(request.getProjectName())
                .build();
        Project saved = projectRepository.save(project);
        return new ProjectResponse(saved.getId(), saved.getProjectBusinessId(), saved.getProjectName());
    }

    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        project.setProjectBusinessId(request.getProjectBusinessId());
        project.setProjectName(request.getProjectName());
        Project saved = projectRepository.save(project);
        return new ProjectResponse(saved.getId(), saved.getProjectBusinessId(), saved.getProjectName());
    }

    public void deleteProject(Long id) {
        projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        projectRepository.deleteById(id);
    }
}
