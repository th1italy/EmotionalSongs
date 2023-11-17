package emotionalsongs;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Alessandro Bugno
 * @author Daniele Maccagnan
 * @author Tommaso Mariani
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Theme(value= "")
public class DemoApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
