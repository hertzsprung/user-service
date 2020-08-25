package uk.co.datumedge.bpdts.repository;

import org.springframework.web.client.RestTemplate;
import uk.co.datumedge.bpdts.model.Users;

public class RestUserRepository implements UserRepository {
    private final RestTemplate client;

    public RestUserRepository(RestTemplate client) {
        this.client = client;
    }

    @Override
    public Users findAllLivingIn(String city) {
        return client.getForObject("/city/{city}/users", Users.class, city);
    }

    @Override
    public Users findAll() {
        return client.getForObject("/users", Users.class);
    }
}
