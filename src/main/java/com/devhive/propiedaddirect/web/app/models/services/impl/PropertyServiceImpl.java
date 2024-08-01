package com.devhive.propiedaddirect.web.app.models.services.impl;

import com.devhive.propiedaddirect.web.app.models.beans.PropertyBean;
import com.devhive.propiedaddirect.web.app.models.daos.IPropertyDao;
import com.devhive.propiedaddirect.web.app.models.entities.Agent;
import com.devhive.propiedaddirect.web.app.models.entities.Client;
import com.devhive.propiedaddirect.web.app.models.entities.Property;
import com.devhive.propiedaddirect.web.app.models.entities.State;
import com.devhive.propiedaddirect.web.app.models.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropertyServiceImpl implements IPropertyService {

    @Autowired
    private IPropertyDao propertyDao;

    @Override
    @Transactional(readOnly = true)
    public List<Property> findAll() {
        return propertyDao.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    @Transactional(readOnly = true)
    public Property findOne(Long id) {
        return propertyDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Property property) {
        propertyDao.save(property);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        propertyDao.deleteById(id);
    }

    public Property convertToEntity(PropertyBean propertyBean) {
        Property property = new Property();
        State state = new State();
        state.setStateId(propertyBean.getState());

        Client client = new Client();
        client.setClientId(propertyBean.getClient());

        Agent agent = new Agent();
        agent.setAgentId(propertyBean.getAgent());

        if(propertyBean.getPropertyId()!=null) {
        	 property.setPropertyId(propertyBean.getPropertyId());
        }
        property.setAddress(propertyBean.getAddress());
        property.setColony(propertyBean.getColony());
        property.setPhone(propertyBean.getPhone());
        property.setCapacity(propertyBean.getCapacity());
        property.setCity(propertyBean.getCity());
        property.setState(state);
        property.setZipCode(propertyBean.getZipCode());
        property.setPrice(propertyBean.getPrice());
        property.setSize(propertyBean.getSize());
        property.setBedrooms(propertyBean.getBedrooms());
        property.setBathrooms(propertyBean.getBathrooms());
        property.setStatus(propertyBean.getStatus());
        property.setSaleRent(propertyBean.getSaleRent());
        property.setType(propertyBean.getType());
        property.setGarage(propertyBean.getGarage());
        property.setParking(propertyBean.getParking());
        property.setImageBase64(propertyBean.getImageBase64());
        property.setClient(client);
        property.setAgent(agent);
        return property;
    }
    
    public PropertyBean convertToBean(Property property) {
        PropertyBean propertyBean = new PropertyBean();
        if(property.getPropertyId()!=null) {
            propertyBean.setPropertyId(property.getPropertyId());
       }

        propertyBean.setAddress(property.getAddress());
        propertyBean.setColony(property.getColony());
        propertyBean.setPhone(property.getPhone());
        propertyBean.setCapacity(property.getCapacity());
        propertyBean.setCity(property.getCity());
        propertyBean.setState(property.getState().getStateId());
        propertyBean.setZipCode(property.getZipCode());
        propertyBean.setPrice(property.getPrice());
        propertyBean.setSize(property.getSize());
        propertyBean.setBedrooms(property.getBedrooms());
        propertyBean.setBathrooms(property.getBathrooms());
        propertyBean.setStatus(property.getStatus());
        propertyBean.setSaleRent(property.getSaleRent());
        propertyBean.setType(property.getType());
        propertyBean.setGarage(property.getGarage());
        propertyBean.setParking(property.getParking());
        propertyBean.setImageBase64(property.getImageBase64());
        propertyBean.setClient(property.getClient().getClientId());
        propertyBean.setClientName(property.getClient().getFirstName() + " "+ property.getClient().getLastName());
        propertyBean.setAgent(property.getAgent().getAgentId());
        return propertyBean;
    }

    @Override
    public List<Property> findBySaleRent(String saleRent) {
        return propertyDao.findBySaleRent(saleRent);
    }

}
