package com.zaid.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskDtos {
    public static class TaskCreateRequest {
        @NotBlank
        private String title;
        private String description;
        @NotNull
        private Long projectId;
        private Long assigneeId;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Long getProjectId() { return projectId; }
        public void setProjectId(Long projectId) { this.projectId = projectId; }
        public Long getAssigneeId() { return assigneeId; }
        public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
    }

    public static class TaskUpdateRequest {
        private String title;
        private String description;
        private com.zaid.taskmanager.model.TaskStatus status;
        private Long assigneeId;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public com.zaid.taskmanager.model.TaskStatus getStatus() { return status; }
        public void setStatus(com.zaid.taskmanager.model.TaskStatus status) { this.status = status; }
        public Long getAssigneeId() { return assigneeId; }
        public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
    }
}
