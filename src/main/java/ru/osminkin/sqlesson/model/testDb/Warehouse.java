package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProduct")
    private Product idProduct;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMaterial")
    private Manufacturer idManufacturer;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "deliveryDate")
    private Timestamp deliveryDate;
}
