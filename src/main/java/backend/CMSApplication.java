package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "backend")
public class CMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(CMSApplication.class, args);

    }
}
