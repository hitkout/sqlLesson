package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ordItem")
public class OrdItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOrderTable")
    private OrderTable idOrderTable;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProduct")
    private Product idProduct;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "costOrder")
    private String costOrder;
}
