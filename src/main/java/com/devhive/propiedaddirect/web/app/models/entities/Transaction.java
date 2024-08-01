package com.devhive.propiedaddirect.web.app.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionID;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "transaction_date")
    private Date transactionDate;

    @JoinColumn(name = "sale_price")
    private Double salePrice;
}
