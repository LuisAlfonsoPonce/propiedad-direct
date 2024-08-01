package com.devhive.propiedaddirect.web.app.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "agents")
public class Agent implements Serializable {

    @Serial
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Column(name = "phone")
    private String phone;

    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;


    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image_base64", columnDefinition = "text")
    private String imageBase64;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @OneToMany(mappedBy = "agent")
    private List<Property> properties;

    @PrePersist
    private void prePersist(){
        createAt = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        createAt = new Date();
    }

   /* @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private Set<Property> properties;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private Set<Visit> visits;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;*/

}
