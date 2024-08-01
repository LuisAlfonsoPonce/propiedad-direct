package com.devhive.propiedaddirect.web.app.models.beans;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import com.devhive.propiedaddirect.web.app.utils.FileSize;

@Data
public class AgentBean {

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

    
    private String imageBase64;
    
    
    @FileSize(min = 1024, max = 1048576)
    private MultipartFile imageFile;

    private boolean isEdit;
}
