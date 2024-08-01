package com.devhive.propiedaddirect.web.app.models.daos;

import com.devhive.propiedaddirect.web.app.models.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {
}
