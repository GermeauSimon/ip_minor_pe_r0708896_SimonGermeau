package com.ucll.Taskmanager.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SubTaskTests {

    @Test
    public void NewSubTask(){
        SubTask sub = new SubTask("test","testdesc");

        assertEquals(sub.getTitle(),"test");
        assertEquals(sub.getDescription(),"testdesc");
        assertNotNull(sub.getId());
    }

    @Test
    public void EmptySubTask(){
        SubTask sub = new SubTask();

        assertNull(sub.getId());
        assertNull(sub.getDescription());
        assertNull(sub.getTitle());
    }
}
