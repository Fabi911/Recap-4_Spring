package de.webdev.recap4_spring;

import de.webdev.recap4_spring.model.ToDo;
import de.webdev.recap4_spring.model.ToDoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class ToDoIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ToDoRepo toDoRepo;

    @Test
    @DirtiesContext
    void getAllTodosTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                  """));


    }

    @Test
    @DirtiesContext
    void getTodoByIdTest() throws Exception {
        toDoRepo.save(new ToDo("1", "Buy milk from the store", "OPEN"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buy milk from the store",
                            "status": "OPEN"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void postTodoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType("application/json")
                .content("""
                        {
                            "description": "Buy milk from the store",
                            "status": "OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buy milk from the store",
                            "status": "OPEN"
                        }
                        """))
                .andExpect(jsonPath("$.id").exists());


    }

    @Test
    @DirtiesContext
    void deleteTodoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void updateTodoTest() throws Exception {
        toDoRepo.save(new ToDo("1", "Buy milk from the store", "OPEN"));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/1")
                .contentType("application/json")
                .content("""
                        {
                            "description": "Buy milk from the store",
                            "status": "CLOSED"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buy milk from the store",
                            "status": "CLOSED"
                        }
                        """));
    }


}
