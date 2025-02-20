package uk.co.datumedge.bpdts.test.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.datumedge.bpdts.test.TestUsers.MECHELLE_BOAM;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllLivingInLondon() {
        Users actualUsers = userRepository.findAllLivingIn("London");
        assertThat(actualUsers.getUsers()).contains(MECHELLE_BOAM);
    }

    @Test
    public void findAll() {
        Users actualUsers = userRepository.findAll();
        assertThat(actualUsers.getUsers().size()).isEqualTo(1000);
    }
}
