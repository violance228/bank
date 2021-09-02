package com.bank.bank.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String interaction;
    @Column(nullable = false)
    private BigDecimal balanceChangeFrom;
    @Column(nullable = false)
    private BigDecimal balanceChangeTo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card")
    private Card card;
}
