package com.devhive.propiedaddirect.web.app.controllers;

import com.devhive.propiedaddirect.web.app.models.entities.Property;
import com.devhive.propiedaddirect.web.app.models.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inmuebles")
public class ApiPropertyController {

    @Autowired
    private IPropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.findOne(id);
        if (property != null) {
            return new ResponseEntity<>(property, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        propertyService.save(property);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        Property property = propertyService.findOne(id);
        if (property != null) {
            property.setPhone(propertyDetails.getPhone());
            property.setSaleRent(propertyDetails.getSaleRent());
            property.setCapacity(propertyDetails.getCapacity());
            property.setAddress(propertyDetails.getAddress());
            property.setColony(propertyDetails.getColony());
            property.setCity(propertyDetails.getCity());
            property.setState(propertyDetails.getState());
            property.setZipCode(propertyDetails.getZipCode());
            property.setPrice(propertyDetails.getPrice());
            property.setSize(propertyDetails.getSize());
            property.setType(propertyDetails.getType());
            property.setBedrooms(propertyDetails.getBedrooms());
            property.setBathrooms(propertyDetails.getBathrooms());
            property.setGarage(propertyDetails.getGarage());
            property.setParking(propertyDetails.getParking());
            property.setStatus(propertyDetails.getStatus());
            property.setImageBase64(propertyDetails.getImageBase64());
            propertyService.save(property);
            return new ResponseEntity<>(property, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        Property property = propertyService.findOne(id);
        if (property != null) {
            propertyService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
