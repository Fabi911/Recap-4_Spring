package de.webdev.recap4_spring.model;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@With
public record ToDo(String id,String description, String status) {

}
