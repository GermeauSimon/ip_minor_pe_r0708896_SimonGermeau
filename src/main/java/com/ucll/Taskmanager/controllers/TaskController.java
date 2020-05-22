package com.ucll.Taskmanager.controllers;

import com.ucll.Taskmanager.domain.SubTask;
import com.ucll.Taskmanager.domain.Task;
import com.ucll.Taskmanager.dto.SubTaskDTO;
import com.ucll.Taskmanager.dto.TaskDTO;
import com.ucll.Taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService ts){
        this.taskService = ts;
    }

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        return "index";
    }

    @GetMapping(path = "/tasks")
    public String getTasks(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        return "taskoverview";
    }
    @GetMapping(path = "/tasks/{id}")
    public String getTaskById(@PathVariable("id") UUID id, Model model){
        model.addAttribute("ding", taskService.getTasksById(id));
        return "taskdescription";
    }

    @GetMapping("/tasks/new")
    public String getNewTaskForm(Model model){
        model.addAttribute("task", new TaskDTO());
        return "newtaskform";
    }

    @GetMapping(path = "/tasks/edit/{id}")
    public String getEditTask(@PathVariable("id") UUID id, Model model){
        model.addAttribute("task", taskService.getTasksById(id));
        return "edittask";
    }
    @PostMapping(path = "/tasks/edit/{id}")
    public String postEditTask(@PathVariable("id") UUID id, Model model, @ModelAttribute("task") @Valid Task task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "edittask";
        }
        taskService.editTask(id,task);
        return "redirect:/tasks/"+id;
    }

    @PostMapping("/tasks/new")
    public String createNewTask(@ModelAttribute("task") @Valid TaskDTO task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "newtaskform";
        }
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping(path = "/tasks/{id}/sub/create")
    public String createSubTaskGet(@PathVariable("id") UUID id,Model model){
        model.addAttribute("task", taskService.getTasksById(id));
        model.addAttribute("subtask", new SubTaskDTO());
        return "createsubtask";
    }

    @PostMapping(path = "/tasks/{id}/sub/create")
    public String createSubTaskPost(@ModelAttribute("subtask") @Valid SubTaskDTO subtask, @PathVariable("id") UUID id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "createsubtask";
        }
        taskService.createSubTask(id, subtask);
        return "redirect:/tasks/"+id;
    }

}
