package com.globati.repository;

import com.globati.dbmodel.Blog;
import com.globati.enums.BlogApprovalStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {

    @Query("SELECT b FROM blog b WHERE b.blogapprovalstatus =: approved AND b.employeeid =:employeeid")
    List<Blog> getApprovdedBlogsByEmployeeId(@Param("employeeid") Long id, @Param("approved") String approved);

}
