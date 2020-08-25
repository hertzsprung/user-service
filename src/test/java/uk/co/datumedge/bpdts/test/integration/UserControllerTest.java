package uk.co.datumedge.bpdts.test.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.service.UserLocationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static uk.co.datumedge.bpdts.test.TestUsers.TEAGAN_PARSONS;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private TestRestTemplate client;

    @LocalServerPort
    private int port;

    @MockBean
    private UserLocationService service;

    @Test
    public void getUsersLivingOrWithin10MilesOfCoventry() {
        Users expectedUsers = new Users(TEAGAN_PARSONS);
        when(service.findUsersLivingOrNear("Coventry", 10)).thenReturn(expectedUsers);

        Users actualUsers = this.client.getForObject(rootUrl() + "/users/living-or-currently-near/Coventry?within-miles=10", Users.class);
        assertThat(actualUsers).isEqualTo(expectedUsers);
    }

    private String rootUrl() {
        return "http://localhost:" + port;
    }
}
