package com.zaid.taskmanager.service;

import com.zaid.taskmanager.model.Project;
import com.zaid.taskmanager.model.User;
import com.zaid.taskmanager.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projects;

    public ProjectService(ProjectRepository projects) {
        this.projects = projects;
    }

    @Transactional
    public Project create(String name, String description, User owner) {
        Project p = new Project(name, description, owner);
        return projects.save(p);
    }

    public List<Project> byOwner(User owner) {
        return projects.findByOwner(owner);
    }

    public Project get(Long id) {
        return projects.findById(id).orElseThrow();
    }

    @Transactional
    public Project update(Long id, String name, String description) {
        Project p = projects.findById(id).orElseThrow();
        if (name != null) p.setName(name);
        if (description != null) p.setDescription(description);
        return projects.save(p);
    }

    @Transactional
    public void delete(Long id) {
        projects.deleteById(id);
    }
}
