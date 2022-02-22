package org.postgrestest.insertapp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "transaction")
@Entity
public class Transaction implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    private String status;
    private BigDecimal amount;

    @Column(name = "m_id")
    private int merchantId;

    @Column(name = "pay_id")
    private int paymentMethodId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Override
    public boolean isNew() {
        return true;
    }
}
