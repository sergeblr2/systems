package by.bhl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RestApp.class, args);
    }

    @Bean
    public CommandLineRunner firstFill(PersonalComputerRepository repository) {
        return (args) -> {
            repository.save(new PersonalComputer("HP SFF 8300", 11, (byte) 1));
            repository.save(new PersonalComputer("HP 600 G3", 18, (byte) 2));
            repository.save(new PersonalComputer("Lenovo M700", 17, (byte) 1));
        };
    }

}
