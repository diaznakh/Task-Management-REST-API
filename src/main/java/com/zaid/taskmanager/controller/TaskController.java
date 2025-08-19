package com.zaid.taskmanager.controller;

import com.zaid.taskmanager.dto.TaskDtos;
import com.zaid.taskmanager.model.Task;
import com.zaid.taskmanager.model.TaskStatus;
import com.zaid.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService tasks;

    public TaskController(TaskService tasks) {
        this.tasks = tasks;
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody TaskDtos.TaskCreateRequest req) {
        Task t = tasks.create(req.getTitle(), req.getDescription(), req.getProjectId(), req.getAssigneeId());
        return ResponseEntity.ok(t);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> byProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(tasks.byProject(projectId));
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
        return ResponseEntity.ok(tasks.updateStatus(taskId, status));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> get(@PathVariable Long taskId) {
        return ResponseEntity.ok(tasks.get(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> update(@PathVariable Long taskId, @RequestBody TaskDtos.TaskUpdateRequest req) {
        Task t = tasks.update(taskId, req.getTitle(), req.getDescription(), req.getStatus(), req.getAssigneeId());
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long taskId) {
        tasks.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
