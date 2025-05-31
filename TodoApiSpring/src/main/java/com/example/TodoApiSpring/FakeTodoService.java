package com.example.TodoApiSpring;


import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService{
     @TimeMonitor
    public String doSomething(){
        for (long i=0;i<10000000000L;i++){

        }
         return "Something";
    } //dosomething method is join point
}
