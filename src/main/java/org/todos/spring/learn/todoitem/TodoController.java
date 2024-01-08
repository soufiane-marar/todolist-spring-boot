package org.todos.spring.learn.todoitem;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService1) {
        this.todoService = todoService1;
    }

    @GetMapping(path = "todos")
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @PostMapping(path = "todo")
    public void registerNewTodo(@RequestBody Todo todo) {

        todoService.addNewTodo(todo);
    }

    @PutMapping(path = "todo/{todoId}")
    public void updateTodo(@PathVariable("todoId") @NonNull Long id, @RequestParam String description, HttpServletResponse response) {

        if (!todoService.todoExists(id)) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return;
        }

        todoService.updateTodo(description, id);
    }

    @DeleteMapping(path = "todo/{todoId}")
    public void deleteTodo(@PathVariable("todoId") @NonNull Long id, HttpServletResponse response) {

        if (!todoService.todoExists(id)) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return;
        }

        todoService.deleteTodo(id);
    }
}
