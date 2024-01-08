package org.todos.spring.learn.todoitem;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todos.spring.learn.user.User;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodoList() {

        return todoRepository.findAll();
    }

    public void addNewTodo(Todo todo) {
        todo.setCreatedBy(new User(1000L));
        todoRepository.save(todo);
    }

    @Transactional
    public void updateTodo(String description, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("todo item not found"));

        todo.setDescription(description);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public boolean todoExists(Long id) {
        return todoRepository.existsById(id);
    }
}
