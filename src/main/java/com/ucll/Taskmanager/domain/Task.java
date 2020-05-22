package com.ucll.Taskmanager.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<SubTask> subtasks;

    public Task(String title, LocalDateTime duedate, String description, List<SubTask> subt) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.duedate = duedate;
        this.description = description;
        this.subtasks = subt;
    }
    public Task(String title, LocalDateTime duedate, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.duedate = duedate;
        this.description = description;
        this.subtasks = new ArrayList<>();
    }
    public Task(){
        this.id = UUID.randomUUID();
        this.subtasks = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public List<SubTask> getSubtasks() {
        return subtasks;
    }

    public void addSubtasks(SubTask subtask) {
        subtasks.add(subtask);
    }
}
