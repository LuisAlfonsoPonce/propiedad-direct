package com.devhive.propiedaddirect.web.app.models.services;

import com.devhive.propiedaddirect.web.app.models.entities.Client;

import java.util.List;

public interface IClientService {
    List<Client> findAll();
    Client findOne(Long clientId);
    void save(Client client);
    void delete (Long clientId);
}
