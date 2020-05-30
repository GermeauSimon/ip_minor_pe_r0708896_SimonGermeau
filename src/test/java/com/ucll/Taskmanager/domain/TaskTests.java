package com.ucll.Taskmanager.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTests {

    @Test
    public void TestNewTaskWithoutSubtasks(){
        Task t = new Task("test", LocalDateTime.of(1999,7,27,13,15),"testdesc");

        assertNotNull(t);
        assertEquals(t.getTitle() , "test");
        assertEquals(t.getDescription() , "testdesc");
        assertEquals(t.getDuedate() , LocalDateTime.of(1999,7,27,13,15));
        assertTrue(t.getSubtasks().isEmpty());
        assertNotNull(t.getId());
    }
    @Test
    public void TestNewTaskWithSubtasks(){
        SubTask sub = new SubTask("test","testdesc");
        List<SubTask> subTasks = new ArrayList<>();
        subTasks.add(sub);
        Task t = new Task("test", LocalDateTime.of(1999,7,27,13,15),"testdesc", subTasks);

        assertNotNull(t);
        assertEquals(t.getTitle() , "test");
        assertEquals(t.getDescription() , "testdesc");
        assertEquals(t.getDuedate() , LocalDateTime.of(1999,7,27,13,15));
        assertFalse(t.getSubtasks().isEmpty());
        assertNotNull(t.getId());
    }

    @Test
    public void testEmptyTask(){
        Task t = new Task();

        assertNotNull(t);
        assertTrue(t.getSubtasks().isEmpty());
    }
}
