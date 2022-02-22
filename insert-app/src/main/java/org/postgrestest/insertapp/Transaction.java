package org.postgrestest.insertapp;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "transaction")
@Entity
public class Transaction {

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

    private LocalDateTime timestamp;
}
