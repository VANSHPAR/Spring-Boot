package com.example.Spring.JPA.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class TodoController {
    @Autowired
    TodoRepository todoRepository;
    @GetMapping("/")
    public String getTodos(){
        List<Todo> todos=todoRepository.findAll();
        return "Get Todos..."; 
    }
}
