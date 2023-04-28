package ru.osminkin.sqlesson.model.basicDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "level")
    private String level;
}
