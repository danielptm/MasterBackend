package com.globati.repository;

import com.globati.mysql.dbmodel.PropertyInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by daniel on 1/17/17.
 */
public interface PropertyInfoRepsitory extends CrudRepository<PropertyInfo, Long> {

    public PropertyInfo getByPropertyId(Long id);

    public PropertyInfo getByAuthToken(String token);

    public PropertyInfo getByFacebookId(String id);

    @Query("SELECT e FROM PropertyInfo e")
    public List<PropertyInfo> getAllPropertyInfos();


}
