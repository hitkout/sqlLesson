package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idType")
    private TypeProduct idType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMaterial")
    private Material idMaterial;
    @Column(name = "productName")
    private String productName;
    @Column(name = "cost")
    private String cost;
}
