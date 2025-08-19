package com.zaid.taskmanager.controller;

import com.zaid.taskmanager.dto.ProjectDtos;
import com.zaid.taskmanager.model.Project;
import com.zaid.taskmanager.model.User;
import com.zaid.taskmanager.service.ProjectService;
import com.zaid.taskmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projects;
    private final UserService users;

    public ProjectController(ProjectService projects, UserService users) {
        this.projects = projects;
        this.users = users;
    }

    @PostMapping
    public ResponseEntity<Project> create(@Valid @RequestBody ProjectDtos.ProjectCreateRequest req) {
        User owner = users.findById(req.getOwnerId()).orElseThrow();
        Project p = projects.create(req.getName(), req.getDescription(), owner);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Project>> byOwner(@PathVariable Long ownerId) {
        User owner = users.findById(ownerId).orElseThrow();
        return ResponseEntity.ok(projects.byOwner(owner));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        return ResponseEntity.ok(projects.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @Valid @RequestBody ProjectDtos.ProjectUpdateRequest req) {
        Project p = projects.update(id, req.getName(), req.getDescription());
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projects.delete(id);
        return ResponseEntity.noContent().build();
    }
}
