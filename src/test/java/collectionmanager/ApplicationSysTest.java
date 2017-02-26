package collectionmanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ApplicationSysTest {

    TestRestTemplate template = new TestRestTemplate();

    @Test
    void contextLoads() {
        // no content
    }

    @Test
    void crudOperationsForGames() {

    }

}
