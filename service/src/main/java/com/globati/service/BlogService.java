package com.globati.service;

import com.globati.dbmodel.Blog;
import com.globati.dbmodel.Employee;
import com.globati.enums.BlogApprovalStatus;
import com.globati.repository.BlogRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@Service
public class BlogService {

    private static final Logger log = LogManager.getLogger(BlogService.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BlogRepository blogRepository;

    public Blog createBlog(Long employeeId, String title, String cityAbout, String description, String blogLink, String imageLink) throws  ServiceException {
        try{
            Employee employee = employeeService.getEmployeeById(employeeId);
            Blog blog = new Blog(employee, cityAbout, title, description, blogLink, imageLink);
            Blog createdBlog = blogRepository.save(blog);
            SendMail.sendCustomMailToGlobatiStaff("daniel@globati.com", "A blog was created by sombody with email: "+employee.getEmail());
            SendMail.sendCustomMailToGlobatiStaff("oliver@globati.com", "A blog was created by sombody with email: "+employee.getEmail());
            return createdBlog;

        }catch(Exception e){
            log.warn("** GLOBATI SERVICE EXCEPTION: There was an error when creating a blog  with title: "+title);
            e.printStackTrace();
            throw new ServiceException("Was not able to create a blog");
        }
    }

    public List<Blog> getApprovedBlogsByEmployeeId(Long id) throws ServiceException {
        try{
            return blogRepository.getApprovdedBlogsByEmployeeId(id, BlogApprovalStatus.APPROVED);
        }catch(Exception e){
            log.warn("*** GLOBATI SERVICE EXCEPTION: There was an error when retrieving blogs for user with id: "+id);
            e.printStackTrace();
            throw new ServiceException("Was not able to retrieve blogs.");
        }
    }

    public Long removeBlog(Long id){
        Blog blog = null;
        try{
            blog = blogRepository.findOne(id);
            blog.setBlogApprovalStatus(BlogApprovalStatus.DELETED_BY_USER);
        }catch(Exception e){
            log.warn("*** GLOBATI SERVICE EXCEPTION: There was an error when updating a blog to delete stats with blog id: "+id);
        }
        return blogRepository.save(blog).getId();
    }
}
