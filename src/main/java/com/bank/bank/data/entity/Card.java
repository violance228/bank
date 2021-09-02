package com.bank.bank.data.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "card_table")
@Data
public class Card {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String pinCode;
    @Column(nullable = false)
    private BigDecimal balance;
}
