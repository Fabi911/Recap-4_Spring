package de.webdev.recap4_spring.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepo extends MongoRepository<ToDo, String> {
}
