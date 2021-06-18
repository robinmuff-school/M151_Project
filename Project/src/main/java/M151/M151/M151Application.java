package M151.M151;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class M151Application {

	public static void main(String[] args) {
		SpringApplication.run(M151Application.class, args);
	}

}
