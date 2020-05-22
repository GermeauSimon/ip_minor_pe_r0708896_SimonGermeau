package com.ucll.Taskmanager.service;


import com.ucll.Taskmanager.domain.SubTask;
import com.ucll.Taskmanager.domain.Task;
import com.ucll.Taskmanager.dto.SubTaskDTO;
import com.ucll.Taskmanager.dto.TaskDTO;
import java.util.List;
import java.util.UUID;


public interface TaskService {

    List<TaskDTO> getTasks();

    void addTask(TaskDTO task);

    TaskDTO getTaskDTOByID(UUID id);

    Task getTasksById(UUID id);

    void editTask(UUID id, Task task);

    void editTaskDTO(TaskDTO taskDTO);

    void createSubTask(UUID id, SubTaskDTO subTaskDTO);
}
