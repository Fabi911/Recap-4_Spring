package de.webdev.recap4_spring.controller;


import de.webdev.recap4_spring.model.ToDo;
import de.webdev.recap4_spring.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {


    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> getToDos() {
        return toDoService.getToDos();
    }

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable String id) {
        return toDoService.getToDoById(id);
    }

    @PostMapping
    public ToDo createToDo(@RequestBody ToDo toDo) {
        return toDoService.createToDo(toDo);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@RequestBody ToDo toDo,@PathVariable String id) {
        return toDoService.updateToDo(toDo,id);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
    }
}
