package com.zaid.taskmanager.repository;

import com.zaid.taskmanager.model.Project;
import com.zaid.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User owner);
}
