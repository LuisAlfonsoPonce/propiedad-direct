package com.devhive.propiedaddirect.web.app.models.services.impl;

import com.devhive.propiedaddirect.web.app.models.daos.IClientDao;
import com.devhive.propiedaddirect.web.app.models.entities.Client;
import com.devhive.propiedaddirect.web.app.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findOne(Long clientId) {
        return clientDao.findById(clientId).orElse(null);
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    @Transactional
    public void delete(Long clientId) {
        clientDao.deleteById(clientId);
    }
}
