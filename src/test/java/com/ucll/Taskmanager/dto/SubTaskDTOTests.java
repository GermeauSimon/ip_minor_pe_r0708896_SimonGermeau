package com.ucll.Taskmanager.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubTaskDTOTests {

    @Test
    public void CreateEmptySubTaskDTO(){
        SubTaskDTO sub = new SubTaskDTO();

        assertNull(sub.getDescription());
        assertNull(sub.getId());
        assertNull(sub.getTitle());
    }

    @Test
    public void CreateSubTaskDTO(){
        SubTaskDTO subTaskDTO = new SubTaskDTO();

        subTaskDTO.setDescription("testdesc");
        subTaskDTO.setId(UUID.randomUUID());
        subTaskDTO.setTitle("test");

        assertEquals(subTaskDTO.getDescription(),"testdesc");
        assertEquals(subTaskDTO.getTitle(),"test");
        assertNotNull(subTaskDTO.getId());
    }
}
