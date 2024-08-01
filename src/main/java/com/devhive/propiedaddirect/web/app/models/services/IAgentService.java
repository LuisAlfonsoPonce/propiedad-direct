package com.devhive.propiedaddirect.web.app.models.services;

import com.devhive.propiedaddirect.web.app.models.beans.AgentBean;
import com.devhive.propiedaddirect.web.app.models.entities.Agent;

import java.util.List;

public interface IAgentService {
    List<Agent> findAll();
    void save(Agent agent);
    Agent findOne(Long id);
    void delete(Long id);
    Agent convertToEntity(AgentBean agentBean);
    AgentBean convertToBean(Agent agent);
}
