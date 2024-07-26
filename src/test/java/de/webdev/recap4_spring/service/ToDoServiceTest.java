package de.webdev.recap4_spring.service;

import de.webdev.recap4_spring.model.ToDo;
import de.webdev.recap4_spring.model.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    ToDoRepo toDoRepo=mock(ToDoRepo.class);
    idService idService=mock(idService.class);
    ToDoService toDoService=new ToDoService(toDoRepo,idService);


    @Test
    void getToDos_EmptyLis() {
        List<ToDo> toDos=toDoService.getToDos();

        List<ToDo> expected= new ArrayList<>();
        assertEquals(expected,toDos);
    }

    @Test
    void getToDos() {
        //GIVEN
        ToDo toDo=new ToDo("123", "Test ToDo", "OPEN");
        List<ToDo> toDos=List.of(toDo);
        when(toDoRepo.findAll()).thenReturn(toDos);
        //WHEN
        List<ToDo> expected=toDoService.getToDos();
        //THEN
        List<ToDo> actual=new ArrayList<>();
        actual.add(new ToDo("123", "Test ToDo", "OPEN"));

        verify(toDoRepo).findAll();
        assertEquals(expected,actual);
    }


    @Test
    void getToDoById_Success() {
        //GIVEN
        ToDo toDo=new ToDo("123", "Test ToDo", "OPEN");
        when(toDoRepo.findById("123")).thenReturn(Optional.of(toDo));
        //WHEN
        ToDo actual = toDoService.getToDoById("123");
        //THEN
        assertEquals("123", actual.id());
        assertEquals("Test ToDo", actual.description());
        assertEquals("OPEN", actual.status());
        verify(toDoRepo).findById("123");
    }

    @Test
    void getToDoById_NotFound() {
        //GIVEN
        when(toDoRepo.findById("123")).thenReturn(Optional.empty());
        //WHEN
        ToDo actual = toDoService.getToDoById("123");
        //THEN
        assertNull(actual);
        verify(toDoRepo).findById("123");
    }

    @Test
    void addToDoTest() {
        //GIVEN
        String generatedId= "12345";
        when(idService.generateId()).thenReturn(generatedId);
        ToDo toDo=new ToDo(generatedId, "Test ToDo", "OPEN");
        when(toDoRepo.save(toDo)).thenReturn(toDo);
        //WHEN
        ToDo actual = toDoService.createToDo(toDo);
        //THEN
        assertEquals(generatedId, actual.id());
        assertEquals("Test ToDo", actual.description());
        assertEquals("OPEN", actual.status());
        verify(toDoRepo).save(toDo);
    }

    @Test
    void updateToDo() {
        //GIVEN
       String existingID="123";
        ToDo updatedToDo=new ToDo("123456", "Test ToDo", "OPEN");
        when(toDoRepo.existsById(existingID)).thenReturn(true);
        doNothing().when(toDoRepo).deleteById(existingID);
        when(toDoRepo.save(any(ToDo.class))).thenReturn(updatedToDo);
        //WHEN
        ToDo actual = toDoService.updateToDo(updatedToDo,existingID);
        //THEN
        assertEquals("123456", actual.id());
        assertEquals("Test ToDo", actual.description());
        assertEquals("OPEN", actual.status());
    }

    @Test
    void deleteToDo() {
        //GIVEN
        String existingID="123";
        doNothing().when(toDoRepo).deleteById(existingID);
        //WHEN
        toDoService.deleteToDo(existingID);
        //THEN
        verify(toDoRepo).deleteById(existingID);
    }
}