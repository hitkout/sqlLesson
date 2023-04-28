package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manufacturerCategory")
public class ManufacturerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategory")
    private CategoryProduct idCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idManufacturer")
    private Manufacturer idManufacturer;
}
