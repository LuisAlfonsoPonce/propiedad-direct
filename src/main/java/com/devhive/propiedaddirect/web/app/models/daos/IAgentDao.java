package com.devhive.propiedaddirect.web.app.models.daos;

import com.devhive.propiedaddirect.web.app.models.entities.Agent;
import org.springframework.data.repository.CrudRepository;

public interface IAgentDao extends CrudRepository<Agent, Long> {
}
