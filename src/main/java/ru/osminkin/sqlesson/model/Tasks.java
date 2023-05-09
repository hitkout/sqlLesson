package ru.osminkin.sqlesson.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "taskText")
    private String taskText;
    @Column(name = "taskResult")
    private String taskResult;
}
