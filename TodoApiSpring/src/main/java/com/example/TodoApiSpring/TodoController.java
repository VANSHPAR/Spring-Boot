package com.example.TodoApiSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //using this annotation we indicate that this class is controller
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;

    private TodoService todoService;
    private TodoService todoService2;

    public TodoController(@Qualifier("anotherTodoService") TodoService todoService,@Qualifier("fakeTodoService") TodoService todoService2){
        this.todoService=todoService;
        this.todoService2=todoService2;
        todoList =new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2,true,"Todo 2",2));

    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        System.out.println(this.todoService.doSomething());
        System.out.println(this.todoService2.doSomething());
        return ResponseEntity.ok(todoList);
    }

//    @GetMapping
//    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false, defaultValue = "true") boolean isCompleted){
//        System.out.println("Incoming Query params" + isCompleted);
//        return ResponseEntity.ok(todoList);
//    }


//    @ResponseStatus(HttpStatus.CREATED )
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.ok(newTodo);

    }
    @GetMapping("/{todoid}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoid){
        for(Todo x : todoList){
            if(x.getId()==todoid) return ResponseEntity.ok(x);
        }
        return ResponseEntity.notFound().build();
    }
   @DeleteMapping("/{todoid}")
    public ResponseEntity<Todo> DeleteTodoById(@PathVariable Long todoid){
        for(Todo x : todoList) {
            if (x.getId() == todoid) {
                Todo t = x;
                todoList.remove(x);
                return ResponseEntity.ok(t);
            }

        }
        return ResponseEntity.notFound().build();

    }
    @PatchMapping("/{todoid}/{s}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long todoid,@PathVariable String s){
        for(Todo x : todoList) {
            if (x.getId() == todoid) {
                x.setTitle(s);
                return ResponseEntity.ok(x);
            }

        }
        return ResponseEntity.notFound().build();

    }


}
