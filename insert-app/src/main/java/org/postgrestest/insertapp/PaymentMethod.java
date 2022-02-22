package org.postgrestest.insertapp;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "payment_method")
@Entity
public class PaymentMethod {

    @Id
    private int id;
    private String name;
}
