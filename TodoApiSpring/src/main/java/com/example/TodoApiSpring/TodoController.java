package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Todo>> getTodos(){
        return ResponseEntity.ok(todoList);
    }


//    @ResponseStatus(HttpStatus.CREATED )
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.ok(newTodo);

    }
    @GetMapping("/todos/{todoid} ")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoid){
        for(Todo x : todoList){
            if(x.getId()==todoid) return ResponseEntity.ok(x);
        }
      return ResponseEntity.notFound().build();
    }

}
