package org.todos.spring.learn.todoitem;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.todos.spring.learn.user.User;
import org.todos.spring.learn.user.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {


    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> getTodoList(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return todoRepository.findByCreatedBy(user);
    }

    public void addNewTodo(Todo todo) {

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow();

        todo.setCreatedBy(user);
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
