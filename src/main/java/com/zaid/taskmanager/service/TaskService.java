package com.zaid.taskmanager.service;

import com.zaid.taskmanager.model.*;
import com.zaid.taskmanager.repository.ProjectRepository;
import com.zaid.taskmanager.repository.TaskRepository;
import com.zaid.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository tasks;
    private final ProjectRepository projects;
    private final UserRepository users;

    public TaskService(TaskRepository tasks, ProjectRepository projects, UserRepository users) {
        this.tasks = tasks;
        this.projects = projects;
        this.users = users;
    }

    @Transactional
    public Task create(String title, String description, Long projectId, Long assigneeId) {
        Project project = projects.findById(projectId).orElseThrow();
        User assignee = null;
        if (assigneeId != null) {
            assignee = users.findById(assigneeId).orElseThrow();
        }
        Task t = new Task(title, description, TaskStatus.TODO, project, assignee);
        return tasks.save(t);
    }

    public List<Task> byProject(Long projectId) {
        Project project = projects.findById(projectId).orElseThrow();
        return tasks.findByProject(project);
    }

    @Transactional
    public Task updateStatus(Long taskId, TaskStatus status) {
        Task t = tasks.findById(taskId).orElseThrow();
        t.setStatus(status);
        return tasks.save(t);
    }

    public Task get(Long taskId) {
        return tasks.findById(taskId).orElseThrow();
    }

    @Transactional
    public Task update(Long taskId, String title, String description, TaskStatus status, Long assigneeId) {
        Task t = tasks.findById(taskId).orElseThrow();
        if (title != null) t.setTitle(title);
        if (description != null) t.setDescription(description);
        if (status != null) t.setStatus(status);
        if (assigneeId != null) {
            User assignee = users.findById(assigneeId).orElseThrow();
            t.setAssignee(assignee);
        }
        return tasks.save(t);
    }

    @Transactional
    public void delete(Long taskId) {
        tasks.deleteById(taskId);
    }
}
