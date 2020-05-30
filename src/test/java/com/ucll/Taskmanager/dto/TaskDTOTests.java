package com.ucll.Taskmanager.dto;

import com.ucll.Taskmanager.domain.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDTOTests {

    @Test
    public void CreateNewTasktDTOTestWithSubTasks(){
        SubTaskDTO sub = new SubTaskDTO();
        List<SubTaskDTO> subtasks = new ArrayList<>();
        subtasks.add(sub);

        TaskDTO t = new TaskDTO();
        t.setDescription("testdesc");
        t.setTitle("test");
        t.setId(UUID.randomUUID());
        t.setDuedate(LocalDateTime.of(1999,7,27,13,15));
        t.setSubtasks(subtasks);

        assertNotNull(t);
        assertEquals(t.getTitle() , "test");
        assertEquals(t.getDescription() , "testdesc");
        assertEquals(t.getDuedate() , LocalDateTime.of(1999,7,27,13,15));
        assertFalse(t.getSubtasks().isEmpty());
        assertNotNull(t.getId());
    }

    @Test
    public void CreateNewTasktDTOTestWithoutSubTasks(){

        TaskDTO t = new TaskDTO();
        t.setDescription("testdesc");
        t.setTitle("test");
        t.setId(UUID.randomUUID());
        t.setDuedate(LocalDateTime.of(1999,7,27,13,15));

        assertNotNull(t);
        assertEquals(t.getTitle() , "test");
        assertEquals(t.getDescription() , "testdesc");
        assertEquals(t.getDuedate() , LocalDateTime.of(1999,7,27,13,15));
        assertNull(t.getSubtasks());
        assertNotNull(t.getId());
    }

    @Test
    public void CreateEmptyTaskDTO(){
        TaskDTO t = new TaskDTO();

        assertNull(t.getSubtasks());
        assertNull(t.getDescription());
        assertNull(t.getDuedate());
        assertNull(t.getTitle());
        assertNull(t.getId());

    }
}
