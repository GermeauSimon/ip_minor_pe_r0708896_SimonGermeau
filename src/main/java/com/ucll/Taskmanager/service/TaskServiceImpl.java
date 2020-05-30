package com.ucll.Taskmanager.service;

import com.ucll.Taskmanager.domain.SubTask;
import com.ucll.Taskmanager.domain.Task;
import com.ucll.Taskmanager.dto.SubTaskDTO;
import com.ucll.Taskmanager.dto.TaskDTO;
import com.ucll.Taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repo;

    @Autowired
    public TaskServiceImpl(TaskRepository repo){
        this.repo = repo;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return repo.findAll().stream().map(task -> {
            TaskDTO dto = new TaskDTO();
            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setDuedate(task.getDuedate());
            dto.setSubtasks(task.getSubtasks()
                    .stream().map( subTask -> {
                        SubTaskDTO subtaskDTO = new SubTaskDTO();
                        subtaskDTO.setId(subTask.getId());
                        subtaskDTO.setDescription(subTask.getDescription());
                        subtaskDTO.setTitle(subTask.getTitle());

                        return subtaskDTO;
                    }).collect(Collectors.toList())
            );
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setDuedate(taskDTO.getDuedate());
        task.setTitle(taskDTO.getTitle());
        task.setId(taskDTO.getId());
        repo.save(task);
    }

    @Override
    public TaskDTO getTaskDTOByID(UUID id) {
        Task task = repo.getOne(id);

        TaskDTO taskdto = new TaskDTO();
        taskdto.setId(task.getId());
        taskdto.setDuedate(task.getDuedate());
        taskdto.setDescription(task.getDescription());
        taskdto.setTitle(task.getTitle());
        taskdto.setSubtasks(task.getSubtasks()
                .stream().map(subTask -> {
                    SubTaskDTO subTaskDTO = new SubTaskDTO();
                    subTaskDTO.setId(subTask.getId());
                    subTaskDTO.setTitle(subTask.getTitle());
                    subTaskDTO.setDescription(subTask.getDescription());

                    return subTaskDTO;
            }).collect(Collectors.toList())
        );
        return taskdto;
    }

    @Override
    public Task getTasksById(UUID id) {
        Task t =  repo.getOne(id);
        return t;
    }

    @Override
    public void editTask(UUID id, Task task) {
        Task t = repo.getOne(id);
        t.setId(task.getId());
        t.setDescription(task.getDescription());
        t.setDuedate(task.getDuedate());
        t.setTitle(task.getTitle());
        repo.save(t);
    }

    @Override
    public void editTaskDTO(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDuedate(taskDTO.getDuedate());
        task.setDescription(taskDTO.getDescription());
        task.setId(taskDTO.getId());

        repo.save(task);
    }

    @Override
    public void createSubTask(UUID id, SubTaskDTO subTaskDTO) {
        SubTask sub = new SubTask();
        sub.setDescription(subTaskDTO.getDescription());
        sub.setTitle(subTaskDTO.getTitle());
        sub.setId(subTaskDTO.getId());

        Task task = this.getTasksById(id);
        task.addSubtasks(sub);
        repo.save(task);
    }


}
