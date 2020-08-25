package uk.co.datumedge.bpdts.test.system.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import uk.co.datumedge.bpdts.UserLocationServiceApplication;

public class UserLocationServiceSteps {
    private ConfigurableApplicationContext applicationContext;

    @Given("the user service connects to $userApiRootUri")
    public void start(String userApiRootUri) {
        SpringApplication springApplication = new SpringApplication(UserLocationServiceApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        applicationContext = springApplication.run("--userApiRootUri=" + userApiRootUri);
    }

    @AfterScenario
    public void stop() {
        if (applicationContext != null) applicationContext.stop();
    }
}
