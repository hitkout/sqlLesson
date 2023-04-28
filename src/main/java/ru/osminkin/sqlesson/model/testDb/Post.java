package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "postName")
    private String postName;
}
