package pgp.week4.milk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pgp.week4.milk.milk.Milk;
import pgp.week4.milk.milk.MilkService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class MilkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilkApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(MilkService milkService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Milk>> typeReference = new TypeReference<>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
			try {
				List<Milk> milks = mapper.readValue(inputStream,typeReference);
				milkService.saveMilks(milks);
				System.out.println("Milks Saved!");
			} catch (IOException e){
				System.out.println("Unable to save milks: " + e.getMessage());
			}
		};
	}
}
