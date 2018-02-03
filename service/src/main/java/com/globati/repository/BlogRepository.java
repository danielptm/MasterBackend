package com.globati.repository;

import com.globati.dbmodel.Blog;
import com.globati.enums.BlogApprovalStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {

    @Query("SELECT b FROM Blog b WHERE b.employee.id=:id AND b.blogApprovalStatus=:approved")
    List<Blog> getApprovdedBlogsByEmployeeId(@Param("id") Long id, @Param("approved") BlogApprovalStatus approved);

}
