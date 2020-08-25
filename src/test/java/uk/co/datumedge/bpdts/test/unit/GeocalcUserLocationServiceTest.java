package uk.co.datumedge.bpdts.test.unit;

import org.junit.jupiter.api.Test;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;
import uk.co.datumedge.bpdts.service.GeocalcUserLocationService;
import uk.co.datumedge.bpdts.service.UserLocationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.datumedge.bpdts.test.TestUsers.TEAGAN_PARSONS;

public class GeocalcUserLocationServiceTest {
    @Test
    public void findUsersLivingInSheffield() {
        UserRepository repository = mock(UserRepository.class);
        UserLocationService userLocationService = new GeocalcUserLocationService(repository);

        Users usersLivingInSheffield = new Users(TEAGAN_PARSONS);
        when(repository.findUsersLivingIn("Sheffield")).thenReturn(usersLivingInSheffield);

        Users actualUsers = userLocationService.findUsersLivingOrNear("Sheffield", 0);
        assertThat(actualUsers).isEqualTo(usersLivingInSheffield);
    }
}
