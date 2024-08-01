package com.devhive.propiedaddirect.web.app.models.beans;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import com.devhive.propiedaddirect.web.app.utils.FileSize;

@Data
public class PropertyBean {


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

    @NotNull
    private Long state;

    @Min(value = 10000)
    @Max(value = 99999)
    @NotNull
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

    @NotEmpty
    private String status;

 
    private String imageBase64;

    private Long agent;

    private Long client;

    private String clientName;
    
    
    @FileSize(min = 1024, max = 1048576)
    private MultipartFile imageFile;
}
