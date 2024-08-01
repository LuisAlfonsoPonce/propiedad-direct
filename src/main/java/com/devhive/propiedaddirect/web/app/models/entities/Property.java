package com.devhive.propiedaddirect.web.app.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "properties")
public class Property implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @NotNull
    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    private Long phone;

    @NotEmpty
    private String saleRent;

    @NotNull
    private Integer capacity; // optional

    @NotEmpty
    private String address;

    @NotEmpty
    private String colony;

    @NotEmpty
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private State state;

    @Min(value = 10000)
    @Max(value = 99999)
    @NotNull
    @Column(name = "zip_code")
    private Integer zipCode;

    @NotNull
    private Double price;

    private Double size;

    @NotEmpty
    private String type;

    @NotNull
    private Integer bedrooms;

    @NotNull
    private Integer bathrooms;

    private Boolean garage;

    @NotNull
    private Boolean parking;

    private String status;

    @Column(name = "image_base64", columnDefinition = "text")
    private String imageBase64;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client client;

    @PrePersist
    private void prePersist(){
        createAt = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        createAt = new Date();
    }
}
