package org.postgrestest.insertapp;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "merchant")
@Entity
public class Merchant {

    @Id
    private int id;
    private String name;
}
