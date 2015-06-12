package pl.allegro.youth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.allegro.youth.service.InitDataService;

@SpringBootApplication
public class Start implements CommandLineRunner {

    @Autowired
    private InitDataService initDataService;

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initDataService.init();
    }

}
