package com.globati.adapter;

import com.globati.dbmodel.Blog;
import com.globati.deserialization_beans.response.employee.ResponseBlog;
import com.globati.service.BlogService;
import com.globati.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogAdapater {

    @Autowired
    BlogService blogService;

    public ResponseBlog createAndReturnAblog(Long employeeId, String title, String about, String description, String blogLink, String imageLink) throws ServiceException {
        Blog blog = blogService.createBlog(employeeId, title, about, description, blogLink, imageLink);
        return new ResponseBlog(blog.getId(), blog.getCityAbout(), blog.getTitle(), blog.getDescription(), blog.getBlogLink(), blog.getImageLink());
    }

    public ResponseBlog getResponseBlog(Long id, String title, String about, String description, String blogLink, String imageLink){
        return new ResponseBlog(id, title, about, description, blogLink, imageLink);
    }

}
