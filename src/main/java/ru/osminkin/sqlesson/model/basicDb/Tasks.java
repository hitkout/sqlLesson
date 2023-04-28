package ru.osminkin.sqlesson.model.basicDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task")
    private String task;
}
