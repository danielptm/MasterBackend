package com.globati.resources;

import com.globati.adapter.BlogAdapater;
import com.globati.deserialization_beans.request.CreateBlog;
import com.globati.resources.annotations.GlobatiAuthentication;
import com.globati.service.BlogService;
import com.globati.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("blog")
@GlobatiAuthentication
public class BlogResource {

    @Autowired
    BlogAdapater blogAdapater;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateBlog blog) throws ServiceException {
        System.out.println(blog);

        return Response.ok(
                blogAdapater.createAndReturnAblog(
                blog.getEmployeeId(), blog.getTitle(),
                blog.getCityAbout(), blog.getDescription(), blog.getBlogLink(), blog.getImageLink()))
                .build();
    }



}
