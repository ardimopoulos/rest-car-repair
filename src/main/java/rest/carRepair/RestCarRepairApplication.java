package rest.carRepair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EntityScan(basePackageClasses = {RestCarRepairApplication.class, Jsr310JpaConverters.class})
public class RestCarRepairApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCarRepairApplication.class, args);
	}
}
