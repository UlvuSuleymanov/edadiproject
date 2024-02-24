package az.edadi.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource("classpath:application-${ENV}common.proporties")
public class ApplicationStarter {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}

}
