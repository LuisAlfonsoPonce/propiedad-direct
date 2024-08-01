package com.devhive.propiedaddirect.web.app.models.daos;

import com.devhive.propiedaddirect.web.app.models.entities.Agent;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface IAgentDao extends CrudRepository<Agent, Long> {
	 List<Agent> findAll(Sort sort);
}
