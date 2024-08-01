package com.devhive.propiedaddirect.web.app.models.daos;

import com.devhive.propiedaddirect.web.app.models.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientDao extends CrudRepository<Client, Long> {
	@Query("SELECT DISTINCT c FROM Client c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Client> findByName(@Param("name") String name);
}
