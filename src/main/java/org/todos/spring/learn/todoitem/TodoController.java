package org.todos.spring.learn.todoitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService1){
        this.todoService = todoService1;
    }

    @GetMapping(path = "todos")
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }
}
