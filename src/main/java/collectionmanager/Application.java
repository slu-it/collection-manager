package collectionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        startInstance(args);
    }

    public static ConfigurableApplicationContext startInstance(String... args) {
        return SpringApplication.run(Application.class, args);
    }

}
