package com.zaid.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;

public class ProjectDtos {
    public static class ProjectCreateRequest {
        @NotBlank
        private String name;
        private String description;
        private Long ownerId;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Long getOwnerId() { return ownerId; }
        public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    }

    public static class ProjectUpdateRequest {
        private String name;
        private String description;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
