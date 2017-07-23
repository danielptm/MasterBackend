package com.globati;

import com.amazonaws.services.directory.model.ServiceException;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import com.globati.service.EmployeeInfoService;
import com.globati.service.EmployeeService;
import com.globati.service.exceptions.UserDoesNotExistException;
import com.globati.webmodel.PasswordAttempt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by daniel on 4/16/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeResource {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Autowired
    EmployeeService employeeService;


    @Autowired
    EmployeeInfoService employeeInfoService;

    public Employee getEmployee() throws FileNotFoundException, com.globati.service.exceptions.ServiceException, UserDoesNotExistException {
        String uid = UUID.randomUUID().toString();
        File file = new File(getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile());
        InputStream fis = new FileInputStream(file);
        Employee e = employeeService.createEmployee("Daniel", uid+"@me.com", uid, "secret password", 23.234, 23.23, "image", "2308 n 44 st", "seattle", "usa");
        return e;
    }

    public ResponseEntity authenticateUser(Employee employee, String password){

        String url = "/authentication";

        PasswordAttempt pa = new PasswordAttempt(employee.get_globatiUsername(), password);

        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PasswordAttempt> he = new HttpEntity<>(pa, headers);

        ResponseEntity<String> r = this.testRestTemplate.postForEntity(url, he, String.class);

        return r;

    }

    /**
     * Creates a user
     * @throws FileNotFoundException
     * @throws ServiceException
     */
    @Test
    public void succeedCreateEmployee() throws FileNotFoundException, ServiceException {

        String uid = UUID.randomUUID().toString();

        String url = "/employees";

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> bodymap = new LinkedMultiValueMap<String, Object>();

        bodymap.add("firstname", "zzzzzzz");
        bodymap.add("email", "email");
        bodymap.add("username", uid);
        bodymap.add("password", "password");
        bodymap.add("propLat", 22.33);
        bodymap.add("propLong", 33.22);
        bodymap.add("street", "street");
        bodymap.add("city", "coity");
        bodymap.add("country", "country");
        bodymap.add("file", file);

        HttpEntity< MultiValueMap<String, Object> > he = new HttpEntity<>(bodymap, headers);

        ResponseEntity<String> r = this.testRestTemplate.postForEntity(url, he, String.class);

        Assert.assertEquals(HttpStatus.OK, r.getStatusCode());

    }



    @Test
    public void createAnEmployeeButUserExists() throws FileNotFoundException, ServiceException {

        String uid = UUID.randomUUID().toString();

        String url = "/employees";

        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );

        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> bodymap = new LinkedMultiValueMap<String, Object>();

        bodymap.add("firstname", "zzzzzzz");
        bodymap.add("email", "email");
        bodymap.add("username", "dannyboy");
        bodymap.add("password", "password");
        bodymap.add("propLat", 22.33);
        bodymap.add("propLong", 33.22);
        bodymap.add("street", "street");
        bodymap.add("city", "coity");
        bodymap.add("country", "country");
        bodymap.add("file", file);

        HttpEntity<MultiValueMap<String, Object> > he = new HttpEntity<>(bodymap, headers);

        ResponseEntity<String> r = this.testRestTemplate.postForEntity(url, he, String.class);

        //***********************************************************

        HttpHeaders headers2= new HttpHeaders();

        headers2.setContentType(MediaType.MULTIPART_FORM_DATA);


        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> bodymap2 = new LinkedMultiValueMap<String, Object>();

        bodymap2.add("firstname", "zzzzzzz");
        bodymap2.add("email", "email");
        bodymap2.add("username", "dannyboy");
        bodymap2.add("password", "password");
        bodymap2.add("propLat", 22.33);
        bodymap2.add("propLong", 33.22);
        bodymap2.add("street", "street");
        bodymap2.add("city", "coity");
        bodymap2.add("country", "country");
        bodymap2.add("file", file);

        HttpEntity< MultiValueMap<String, Object> > h2 = new HttpEntity<>(bodymap2, headers);

        ResponseEntity<String> r2 = this.testRestTemplate.postForEntity(url, h2, String.class);

        Assert.assertEquals(HttpStatus.EXPECTATION_FAILED, r2.getStatusCode());


    }


    @Test
    public void testUpdateEmployee() throws FileNotFoundException, UserDoesNotExistException, com.globati.service.exceptions.ServiceException {

        Employee employee = getEmployee();
        EmployeeInfo employeeInfo = employeeInfoService.getEmployeeInfoByEmployeeId(employee.get_id());

        ResponseEntity response = authenticateUser(employee, employeeInfo.get_globatiPassword());

        System.out.println("***************************");
//        System.out.println(response.getBody().);


//        File file = new File( getClass().getClassLoader().getResource("test_resources/oasishostel.png").getFile() );
//
//        Employee e4 = getEmployee();
//
//        List<Object> objects = employeeService.getItemsForEmployee(e4.get_globatiUsername());
//
//        System.out.println("*****************************");
//        System.out.println(objects.toString());

//        String url = "/employees?employeeId="+e4.get_id();
//
//        UpdateEmployee updateEmployee = new UpdateEmployee();
//
//        HttpHeaders headers= new HttpHeaders();
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<UpdateEmployee> he = new HttpEntity<>(updateEmployee, headers);
//
//        //String firstName, String lastName, String email, String userName, String globatiPassword, double locLat, double locLong)
//        ResponseEntity<String> r = testRestTemplate.exchange(url, HttpMethod.PUT, he, String.class);
//
//        Assert.assertEquals(HttpStatus.OK, r.getStatusCode());





    }



}
