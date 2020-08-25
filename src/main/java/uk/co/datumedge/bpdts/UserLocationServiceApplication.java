package uk.co.datumedge.bpdts;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.Point;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import uk.co.datumedge.bpdts.repository.RestUserRepository;
import uk.co.datumedge.bpdts.repository.UserRepository;
import uk.co.datumedge.bpdts.service.GeocalcUserLocationService;
import uk.co.datumedge.bpdts.service.UserLocationService;

import java.util.Map;

@SpringBootApplication
@Configuration
public class UserLocationServiceApplication {
    /**
     * Start the driver service.
     * @param args Available configuration options are:
     *             <dl>
     *              <dt><code>--userApiRootUri=&lt;uri&gt;</code></dt>
     *              <dd>Specifies the root URI for the underlying user API
     *              (default is <code>http://bpdts-test-app-v4.herokuapp.com</code>).</dd>
     *             </dl>
     */
    public static void main(String[] args) {
        SpringApplication.run(UserLocationServiceApplication.class, args);
    }

    @Bean
    public UserLocationService userLocationService(UserRepository userRepository, Map<String, Point> cityLocations) {
        return new GeocalcUserLocationService(userRepository, cityLocations);
    }

    @Bean
    public Map<String, Point> cityLocations() {
        Point point = Point.at(Coordinate.fromDegrees(51.507222), Coordinate.fromDegrees(-0.1275));
        return Map.of("London", point);
    }

    @Bean
    public UserRepository userRepository(RestTemplate client) {
        return new RestUserRepository(client);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ApplicationArguments args) {
        String rootUri;
        if (args.containsOption("userApiRootUri") && args.getOptionValues("userApiRootUri").size() > 0) {
            rootUri = args.getOptionValues("userApiRootUri").get(0);
        } else {
            rootUri = "http://bpdts-test-app-v4.herokuapp.com";
        }

        return builder.rootUri(rootUri).build();
    }
}
