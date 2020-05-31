package com.ucll.Taskmanager.controllers;

import com.ucll.Taskmanager.dto.TaskDTO;
import com.ucll.Taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskApiController {

    private final TaskService taskService;

    @Autowired
    public TaskApiController(TaskService ts){
        this.taskService = ts;
    }

    @GetMapping("/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks(){
        return this.taskService.getTasks();
    }
}
