package emotionalsongs;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import emotionalsongs.backend.entities.Song;
import emotionalsongs.backend.repositories.SongsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Alessandro Bugno
 * @author Daniele Maccagnan
 * @author Tommaso Mariani
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Theme(value= "")
public class MainApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}


	@Bean
	public CommandLineRunner tryBean(SongsRepository repository) {
		return (args) -> {
			repository.save(new Song("due", (long) 111, "bho", 2002, "io"));
		};
	}

}
