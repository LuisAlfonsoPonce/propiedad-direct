package com.devhive.propiedaddirect.web.app.models.daos;

import com.devhive.propiedaddirect.web.app.models.entities.State;
import org.springframework.data.repository.CrudRepository;

public interface IStateDao extends CrudRepository<State, Long> {
}
