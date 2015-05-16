package integracyjne;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import controller.Application;
import controller.Kategoria;
import controller.KategoriaRepository;

@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class ApplicationControllerTest {
 
    @Autowired   // 5
    KategoriaRepository repository;
 
    Kategoria auto;
    Kategoria food;
 
    @Value("${local.server.port}")   // 6
    int port;
 
    @Before
    public void setUp() {
        // 7
        auto = new Kategoria();
        food = new Kategoria();
 
        // 8
        repository.deleteAll();
        repository.save(Arrays.asList(auto, food));
 
        // 9
        RestAssured.port = port;
    }
 
    // 10
    @Test
    public void canFetchMickey() {
        Integer autoId = mickey.getId();
 
        when().
                get("/characters/{id}", mickeyId).
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.is("Mickey Mouse")).
                body("id", Matchers.is(mickeyId));
    }
 
    @Test
    public void canFetchAll() {
        when().
                get("/characters").
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
    }
 
    @Test
    public void canDeletePluto() {
        Integer plutoId = pluto.getId();
 
        when()
                .delete("/characters/{id}", plutoId).
        then().
                statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
