package com.devhive.propiedaddirect.web.app.controllers;

import com.devhive.propiedaddirect.web.app.models.beans.BlogBean;
import com.devhive.propiedaddirect.web.app.models.entities.Blog;
import com.devhive.propiedaddirect.web.app.models.services.IBlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Controller
@SessionAttributes("blogBean")
public class BlogController {

    @Value("${application.controllers.titulo}")
    private String titulo;

    @Autowired
    private IBlogService blogService;

    @GetMapping("/list-blogs")
    public String listBlogs(Model model) {
        model.addAttribute("titulo", titulo);
        model.addAttribute("blogs", blogService.findAll());
        return "blogs/list-blogs";
    }

    @GetMapping(value = "/form-blog")
    public String createBlog(Map<String, Object> model) {
        BlogBean blogBean = new BlogBean();
        model.put("titulo", titulo);
        model.put("blogBean", blogBean);
        return "blogs/form-blog";
    }

    @PostMapping("/save-blog")
    public String saveBlog(@Valid BlogBean blogBean, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Crear Blog");
            return "blogs/form-blog";
        }

        Blog blog = blogService.convertToEntity(blogBean);

        MultipartFile imageFile = blogBean.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                blog.setImageBase64(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar la excepción según sea necesario
            }
        }

        blogService.save(blog);
        sessionStatus.setComplete();
        return "redirect:/list-blogs";
    }

    @GetMapping(value = "/form-blog/{blogId}")
    public String editBlog(@PathVariable(value = "blogId") Long blogId, Map<String, Object> model) {
        Blog blog;
        BlogBean blogBean;
        if (blogId > 0) {
            blog = blogService.findOne(blogId);
            blogBean = blogService.convertToBean(blog);
        } else {
            return "redirect:/list-blogs";
        }
        model.put("blogBean", blogBean);
        model.put("titulo", titulo);
        return "blogs/form-blog";
    }

    @GetMapping(value = "/delete-blog/{blogId}")
    public String deleteBlog(@PathVariable(value = "blogId") Long blogId) {
        if (blogId > 0) {
            blogService.delete(blogId);
        }
        return "redirect:/list-blogs";
    }
}
