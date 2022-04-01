package by.bhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// when packages named as "by.bhl.repository", "by.bhl.model" (in different subfolders) -> set scan to them as at next lines:
//@EnableJpaRepositories(basePackages = "by.bhl.repository")
//@EntityScan(basePackages = "by.bhl.model")

// SpringBootApplication (including default @ComponentScan) scanning for this and SUB packages
// so if RestApp in subFolder -> uncomment upper 2 annotations, else move RestApp to UPPER folder above others

@SpringBootApplication
public class RestApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RestApp.class, args);
    }

}
