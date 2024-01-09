package org.todos.spring.learn.todoitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.todos.spring.learn.user.User;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByIsFinished(boolean isFinished);

    List<Todo> findByCreatedBy(User user);
}
