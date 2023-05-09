package ru.osminkin.sqlesson.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lessons")
public class Lessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lesson")
    private String lesson;
}
