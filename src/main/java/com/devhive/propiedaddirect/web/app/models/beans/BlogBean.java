package com.devhive.propiedaddirect.web.app.models.beans;

import com.devhive.propiedaddirect.web.app.models.entities.Comment;
import com.devhive.propiedaddirect.web.app.utils.FileSize;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BlogBean {

    private Long blogId;

    private String title;

    @NotEmpty
    private String saleRent;


    private Long agent;


    private Long client;

    private String description;

    private List<Comment> comments;

    private String imageBase64;

    @FileSize(min = 1024, max = 1048576)
    private MultipartFile imageFile;

}