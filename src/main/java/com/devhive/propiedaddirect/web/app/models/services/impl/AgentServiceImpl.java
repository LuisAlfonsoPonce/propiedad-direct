package com.devhive.propiedaddirect.web.app.models.services.impl;

import com.devhive.propiedaddirect.web.app.models.beans.AgentBean;
import com.devhive.propiedaddirect.web.app.models.daos.IAgentDao;
import com.devhive.propiedaddirect.web.app.models.entities.Agent;
import com.devhive.propiedaddirect.web.app.models.services.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
public class AgentServiceImpl implements IAgentService {

    @Autowired
    private IAgentDao agentDao;

    @Override
    @Transactional(readOnly = true)
    public List<Agent> findAll() {
        return agentDao.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    @Transactional
    public void save(Agent agent) {
        agentDao.save(agent);
    }

    @Override
    @Transactional(readOnly = true)
    public Agent findOne(Long id) {
        return agentDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        agentDao.deleteById(id);
    }


    public Agent convertToEntity(AgentBean agentBean) {
        Agent agent = new Agent();
        if (agentBean.getAgentId() != null) {
            agent.setAgentId(agentBean.getAgentId());
        }
        agent.setFirstName(agentBean.getFirstName());
        agent.setLastName(agentBean.getLastName());
        agent.setPhone(agentBean.getPhone());
        agent.setEmail(agentBean.getEmail());
        agent.setDescription(agentBean.getDescription());
        agent.setImageBase64(agentBean.getImageBase64());
        return agent;
    }

    public AgentBean convertToBean(Agent agent) {
        AgentBean agentBean = new AgentBean();
        if (agent.getAgentId() != null) {
            agentBean.setAgentId(agent.getAgentId());
        }
        agentBean.setFirstName(agent.getFirstName());
        agentBean.setLastName(agent.getLastName());
        agentBean.setPhone(agent.getPhone());
        agentBean.setEmail(agent.getEmail());
        agentBean.setDescription(agent.getDescription());
        agentBean.setImageBase64(agent.getImageBase64());
        return agentBean;
    }


}
