package ru.osminkin.sqlesson.model.testDb;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orderTable")
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idStaff")
    private Staff idStaff;
    @Column(name = "dateOrder")
    private Timestamp dateOrder;
}
