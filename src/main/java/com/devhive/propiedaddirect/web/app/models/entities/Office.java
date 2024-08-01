package com.devhive.propiedaddirect.web.app.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "offices")
public class Office implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Long officeId;

    private String address;

    private String city;

    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    private Integer phone;

   /* @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private Set<Agent> agents;*/
}
