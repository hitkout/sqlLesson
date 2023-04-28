package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "typeProduct")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategory")
    private CategoryProduct idCategory;
    @Column(name = "typeName")
    private String typeName;
}
