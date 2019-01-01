import com.globati.service.JwtService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/DealServiceTest-context.xml"})
@ActiveProfiles("test")
public class TestJwt extends SuperTest{


    @Autowired
    JwtService jwtService;


    @Test
    public void buildJwtAndValidatingEqualsTrue(){

        String apitoken = "hello";

        String jwt = jwtService.buildJwt(apitoken);

        Assert.assertTrue(jwtService.validateApiTokenFromJwt(jwt, apitoken));


    }

    @Test
    public void buildJwtAndValidatingEqualsFalse(){
        String apitoken = "hello";

        String jwt = jwtService.buildJwt(apitoken);

        Assert.assertFalse(jwtService.validateApiTokenFromJwt(jwt, "asdf"));
    }


    @Test
    public void buildJwtAndGetBodyFromJwt(){
        String jwt = jwtService.buildJwt("hello");
        String body = jwtService.getPayloadFromJwt(jwt);

        Assert.assertEquals("hello", body);

    }


    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("hi");
        list.add("bye");

        System.out.println(list);


    }
}
