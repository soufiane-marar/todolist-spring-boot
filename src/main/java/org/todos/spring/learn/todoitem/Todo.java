package org.todos.spring.learn.todoitem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.todos.spring.learn.user.User;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todos")
public class Todo {


    @Id
    @SequenceGenerator(
            name = "todo_sequence",
            sequenceName = "todo_sequence",
            initialValue = 10000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_sequence"
    )
    private Long id;
    private String description;

    private boolean isFinished = false;
    private Date createdAt = new Date();

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "created_by")
    private User createdBy;

    public Todo(String description, boolean isFinished, Date createdAt, User createdBy) {
        this.description = description;
        this.isFinished = isFinished;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isFinished=" + isFinished +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                '}';
    }
}
