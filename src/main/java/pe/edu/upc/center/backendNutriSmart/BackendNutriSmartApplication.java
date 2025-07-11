package pe.edu.upc.center.backendNutriSmart;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing
public class BackendNutriSmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendNutriSmartApplication.class, args);
	}

}
