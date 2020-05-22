package com.ucll.Taskmanager.dto;

import com.ucll.Taskmanager.domain.SubTask;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskDTO {

    private UUID id;

    @NotEmpty
    @Size(min=3)
    private String title;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime duedate;
    @NotEmpty
    @Size(max=250)
    private String description;
    private List<SubTaskDTO> subtasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDateTime duedate) {
        this.duedate = duedate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubTaskDTO> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTaskDTO> subtasks) {
        this.subtasks = subtasks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
