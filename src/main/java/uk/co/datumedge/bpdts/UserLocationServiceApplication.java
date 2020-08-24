package uk.co.datumedge.bpdts;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
public class UserLocationServiceApplication {
    /**
     * Start the driver service.
     * @param args Available configuration options are:
     *             <dl>
     *              <dt><code>--userApiRootUri=&lt;uri&gt;</code></dt>
     *              <dd>Specifies the root URI for the underlying user API
     *              (default is <code>http://bpdts-test-app-v4.herokuapp.com/</code>).</dd>
     *             </dl>
     */
    public static void main(String[] args) {
        SpringApplication.run(UserLocationServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ApplicationArguments args) {
        String rootUri;
        if (args.containsOption("userApiRootUri") && args.getOptionValues("userApiRootUri").size() > 0) {
            rootUri = args.getOptionValues("userApiRootUri").get(0);
        } else {
            rootUri = "http://bpdts-test-app-v4.herokuapp.com/";
        }

        return builder.rootUri(rootUri).build();
    }
}
