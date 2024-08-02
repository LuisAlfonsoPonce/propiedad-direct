package com.devhive.propiedaddirect.web.app.models.services;


import com.devhive.propiedaddirect.web.app.models.beans.BlogBean;
import com.devhive.propiedaddirect.web.app.models.entities.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();

    void save(Blog property);

    Blog findOne(Long id);

    void delete(Long id);

    Blog convertToEntity(BlogBean propertyBean);

    BlogBean convertToBean(Blog property);
}
