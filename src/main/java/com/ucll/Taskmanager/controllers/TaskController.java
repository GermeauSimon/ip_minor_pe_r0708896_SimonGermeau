package com.ucll.Taskmanager.controllers;

import com.ucll.Taskmanager.domain.SubTask;
import com.ucll.Taskmanager.domain.Task;
import com.ucll.Taskmanager.dto.SubTaskDTO;
import com.ucll.Taskmanager.dto.TaskDTO;
import com.ucll.Taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(path = "/tasks/{id}/delete")
    public String deleteTask(@PathVariable("id") UUID id,Model model){
        taskService.deleteTask(id);
        return getTasks(model);
    }

    @PostMapping(path = "/tasks/{id}/sub/create")
    public String createSubTaskPost(@PathVariable("id") UUID id, @ModelAttribute("subtask") @Valid SubTaskDTO subtask, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("task", taskService.getTasksById(id));
            return "createsubtask";
        }
        taskService.createSubTask(id, subtask);
        return "redirect:/tasks/"+id;
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int code = Integer.valueOf(status.toString());

            if(code == HttpStatus.NOT_FOUND.value()){
                return "error-404";
            }else if(code == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "error-500";
            }
        }
        return "error";
    }

}
