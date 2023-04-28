package ru.osminkin.sqlesson.model.basicDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "finishLesson")
public class FinishLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id")
    private Lessons lesson;
}
