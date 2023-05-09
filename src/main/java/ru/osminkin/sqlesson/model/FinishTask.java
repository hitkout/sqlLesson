package ru.osminkin.sqlesson.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "finishTask")
public class FinishTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Tasks task;
}
