package com.devhive.propiedaddirect.web.app.models.services.impl;

import com.devhive.propiedaddirect.web.app.models.beans.BlogBean;
import com.devhive.propiedaddirect.web.app.models.daos.IBlogDao;
import com.devhive.propiedaddirect.web.app.models.entities.Agent;
import com.devhive.propiedaddirect.web.app.models.entities.Blog;
import com.devhive.propiedaddirect.web.app.models.entities.Client;
import com.devhive.propiedaddirect.web.app.models.services.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private IBlogDao blogDao;

    @Override
    @Transactional(readOnly = true)
    public List<Blog> findAll() {
        return blogDao.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    @Transactional(readOnly = true)
    public Blog findOne(Long id) {
        return blogDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Blog blog) {
        blogDao.save(blog);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        blogDao.deleteById(id);
    }

    public Blog convertToEntity(BlogBean blogBean) {
        Blog blog = new Blog();

        if (blogBean.getBlogId() != null) {
            blog.setBlogId(blogBean.getBlogId());
        }

        blog.setTitle(blogBean.getTitle());
        blog.setSaleRent(blogBean.getSaleRent());
        blog.setDescription(blogBean.getDescription());
        blog.setImageBase64(blogBean.getImageBase64());

        Agent agent = new Agent();
        agent.setAgentId(blogBean.getAgent());
        blog.setAgent(agent);

        Client client = new Client();
        client.setClientId(blogBean.getClient());
        blog.setClient(client);

        return blog;
    }

    public BlogBean convertToBean(Blog blog) {
        BlogBean blogBean = new BlogBean();

        if (blog.getBlogId() != null) {
            blogBean.setBlogId(blog.getBlogId());
        }

        blogBean.setTitle(blog.getTitle());
        blogBean.setSaleRent(blog.getSaleRent());
        blogBean.setDescription(blog.getDescription());
        blogBean.setImageBase64(blog.getImageBase64());

        if (blog.getAgent() != null) {
            blogBean.setAgent(blog.getAgent().getAgentId());
        }

        if (blog.getClient() != null) {
            blogBean.setClient(blog.getClient().getClientId());
        }

        return blogBean;
    }
}
