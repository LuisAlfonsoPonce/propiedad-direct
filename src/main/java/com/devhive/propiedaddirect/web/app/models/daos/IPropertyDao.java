package com.devhive.propiedaddirect.web.app.models.daos;

import com.devhive.propiedaddirect.web.app.models.entities.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface IPropertyDao extends CrudRepository<Property, Long> {
    List<Property> findAll(Sort sort);

    List<Property> findBySaleRent(String saleRent);
}
