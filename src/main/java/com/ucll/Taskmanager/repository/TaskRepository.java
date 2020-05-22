package com.ucll.Taskmanager.repository;

import com.ucll.Taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
