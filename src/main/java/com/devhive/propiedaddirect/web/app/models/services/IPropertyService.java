package com.devhive.propiedaddirect.web.app.models.services;

import com.devhive.propiedaddirect.web.app.models.beans.PropertyBean;
import com.devhive.propiedaddirect.web.app.models.entities.Property;

import java.util.List;

public interface IPropertyService {
    List<Property> findAll();

    void save(Property property);

    Property findOne(Long id);

    void delete(Long id);

    Property convertToEntity(PropertyBean propertyBean);
    
    PropertyBean convertToBean(Property property);
}
