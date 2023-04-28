package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
}
