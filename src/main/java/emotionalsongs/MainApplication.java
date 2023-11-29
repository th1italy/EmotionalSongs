package emotionalsongs;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import emotionalsongs.backend.entities.Song;
import emotionalsongs.backend.repositories.SongRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Alessandro Bugno
 * @author Daniele Maccagnan
 * @author Tommaso Mariani
 */
@SpringBootApplication()
@Theme(value= "")
public class MainApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}


	//bean per testare connessione al db
	@Bean
	public CommandLineRunner tryBean(SongRepository repository) {
		return (args) -> {

		};
	}

}
