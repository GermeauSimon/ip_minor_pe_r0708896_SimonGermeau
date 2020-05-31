package com.ucll.Taskmanager.service;

import com.ucll.Taskmanager.dto.SubTaskDTO;
import com.ucll.Taskmanager.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskServiceImplTest {

    @Autowired
    private TaskService service;

    @Test
    @Transactional
    public void addTest(){
        SubTaskDTO sub = new SubTaskDTO();
        List<SubTaskDTO> subtasks = new ArrayList<>();
        subtasks.add(sub);

        TaskDTO t = new TaskDTO();
        t.setDescription("testdesc");
        t.setTitle("test");
        t.setId(UUID.randomUUID());
        t.setDuedate(LocalDateTime.of(2021,7,27,13,15));
        t.setSubtasks(subtasks);

        this.service.addTask(t);

        List<TaskDTO> tasks = this.service.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        TaskDTO tskdto = tasks.get(0);
        assertEquals(tskdto.getTitle(), t.getTitle());
        assertEquals(tasks.size(), 1);
    }

    @Test
    @Transactional
    public void deleteTaskTest(){
        SubTaskDTO sub = new SubTaskDTO();
        List<SubTaskDTO> subtasks = new ArrayList<>();
        subtasks.add(sub);

        TaskDTO t = new TaskDTO();
        t.setDescription("testdesc");
        t.setTitle("test");
        t.setId(UUID.randomUUID());
        t.setDuedate(LocalDateTime.of(2021,7,27,13,15));
        t.setSubtasks(subtasks);

        this.service.addTask(t);
        assertFalse(this.service.getTasks().isEmpty());
        this.service.deleteTask(this.service.getTasks().get(0).getId());
        assertTrue(this.service.getTasks().isEmpty());
    }

    @Test
    @Transactional
    public void addSubTaskTest(){

        UUID id = UUID.randomUUID();

        TaskDTO t = new TaskDTO();
        t.setDescription("testdesc");
        t.setTitle("test");
        t.setId(id);
        t.setDuedate(LocalDateTime.of(2021,7,27,13,15));

        this.service.addTask(t);

        SubTaskDTO sdto = new SubTaskDTO();
        sdto.setTitle("test");
        sdto.setDescription("testdesc");
        sdto.setId(UUID.randomUUID());

        this.service.createSubTask(service.getTasks().get(0).getId(),sdto);

        assertFalse(service.getTasks().get(0).getSubtasks().isEmpty());

   }
}
