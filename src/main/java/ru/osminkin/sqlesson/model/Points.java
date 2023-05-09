package ru.osminkin.sqlesson.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "points")
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "point")
    private Long point;
}
