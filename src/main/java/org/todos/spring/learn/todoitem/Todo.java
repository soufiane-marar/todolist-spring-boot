package org.todos.spring.learn.todoitem;

import jakarta.persistence.*;
import org.todos.spring.learn.user.User;

import java.util.Date;


@Entity
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
    private boolean isFinished;
    private Date createdAt;

    @ManyToOne(targetEntity = User.class)
    private User createdBy;

    public Todo() {
    }

    public Todo(Long id, String description, boolean isFinished, Date createdAt, User createdBy) {
        this.id = id;
        this.description = description;
        this.isFinished = isFinished;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Todo(String description, boolean isFinished, Date createdAt, User createdBy) {
        this.description = description;
        this.isFinished = isFinished;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
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
