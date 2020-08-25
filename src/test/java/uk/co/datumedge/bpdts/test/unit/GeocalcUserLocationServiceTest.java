package uk.co.datumedge.bpdts.test.unit;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.Point;
import org.junit.jupiter.api.Test;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;
import uk.co.datumedge.bpdts.service.GeocalcUserLocationService;
import uk.co.datumedge.bpdts.service.UserLocationService;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.datumedge.bpdts.test.TestUsers.MECHELLE_BOAM;
import static uk.co.datumedge.bpdts.test.TestUsers.TERRY_STOWGILL;

public class GeocalcUserLocationServiceTest {
    @Test
    public void findUsersLivingInOrCurrentlyNearSheffield() {
        UserRepository repository = mock(UserRepository.class);
        UserLocationService userLocationService = new GeocalcUserLocationService(repository, cityLocations());

        Users usersLivingInSheffield = new Users(TERRY_STOWGILL);
        when(repository.findUsersLivingIn("Sheffield")).thenReturn(usersLivingInSheffield);

        Users usersCurrentlyNearSheffield = new Users(MECHELLE_BOAM);
        when(repository.findUsersCurrentlyNear("Sheffield", 53.383333, -1.466667,10)).thenReturn(usersCurrentlyNearSheffield);

        Users actualUsers = userLocationService.findUsersLivingOrNear("Sheffield", 10);
        assertThat(actualUsers).isEqualTo(new Users(TERRY_STOWGILL, MECHELLE_BOAM));
    }

    private Map<String, Point> cityLocations() {
        return Map.of("Sheffield", Point.at(Coordinate.fromDegrees(53.383333), Coordinate.fromDegrees(-1.466667)));
    }
}
