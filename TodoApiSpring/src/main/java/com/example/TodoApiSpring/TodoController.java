package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //using this annotation we indicate that this class is controller
public class TodoController {
    private static List<Todo> todoList;

    public TodoController(){
        todoList =new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2,true,"Todo 2",2));
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoList;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public  void createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);

    }

}
