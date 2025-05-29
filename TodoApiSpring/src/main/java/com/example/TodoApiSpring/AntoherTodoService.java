package com.example.TodoApiSpring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AntoherTodoService implements TodoService{

    @Override
    public String doSomething(){
        return "Something from another todo service";
    }
}
