package org.postgrestest.insertapp;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    private int id;
    private String status;
    private BigDecimal amount;

    @Column(name = "m_id")
    private int merchantId;

    @Column(name = "pay_id")
    private int paymentMethodId;
}
