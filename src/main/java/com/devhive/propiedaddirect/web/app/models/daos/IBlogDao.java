package com.devhive.propiedaddirect.web.app.models.daos;

import com.devhive.propiedaddirect.web.app.models.entities.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBlogDao extends CrudRepository<Blog, Long> {
    List<Blog> findAll(Sort sort);
}
