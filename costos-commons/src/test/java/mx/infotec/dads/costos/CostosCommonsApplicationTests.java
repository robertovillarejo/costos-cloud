package mx.infotec.dads.costos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.costos.repository.DataFrameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.data.mongodb.host=localhost", "spring.data.mongodb.port=27017",
        "spring.data.mongodb.database=costos" })
public class CostosCommonsApplicationTests {

    @Autowired
    private DataFrameRepository repository;

    @Test
    public void testRepository() {
        System.out.println(repository.findByProcessedFalse(PageRequest.of(0, 1)).getContent());
    }

    @SpringBootApplication
    public static class Application {
    }

}
