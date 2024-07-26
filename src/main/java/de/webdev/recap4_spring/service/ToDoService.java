package de.webdev.recap4_spring.service;

import de.webdev.recap4_spring.model.ToDo;
import de.webdev.recap4_spring.model.ToDoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepo toDoRepo;
    private final idService idService;


    public List<ToDo> getToDos() {
        List<ToDo> toDos = toDoRepo.findAll();
        return toDos;
    }
    public ToDo getToDoById(String id) {
        return toDoRepo.findById(id).orElse(null);
    }

    public ToDo createToDo(ToDo toDo) {
        ToDo toDoToAddd = new ToDo(
                idService.generateId(),
                toDo.description(),
                toDo.status()
        );
        return toDoRepo.save(toDoToAddd);
    }

    public ToDo updateToDo(ToDo toDo, String id) {
        if (toDoRepo.existsById(id)) {
            toDoRepo.deleteById(id);
            ToDo toDoToUpdate = new ToDo(
                    idService.generateId(),
                    toDo.description(),
                    toDo.status()
            );
            return toDoRepo.save(toDoToUpdate);
        }
        return null;
    }

    public void deleteToDo(String id) {
        toDoRepo.deleteById(id);
    }
}
