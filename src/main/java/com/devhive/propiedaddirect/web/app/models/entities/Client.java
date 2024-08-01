package com.devhive.propiedaddirect.web.app.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    private Long phone;

    @Column(name = "preferred_contact_method")
    @NotEmpty
    private String preferredContactMethod;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @OneToMany(mappedBy = "client")
    private List<Property> properties;

    @PrePersist
    private void prePersist(){
        createAt = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        createAt = new Date();
    }

   /* @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Visit> visits;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;*/
}
