package uk.co.datumedge.bpdts.test.unit;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.Point;
import org.junit.jupiter.api.Test;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;
import uk.co.datumedge.bpdts.service.GeocalcUserLocationService;
import uk.co.datumedge.bpdts.service.UserLocationService;

import javax.validation.ValidationException;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.datumedge.bpdts.test.TestUsers.*;

public class GeocalcUserLocationServiceTest {
    private final UserRepository repository = mock(UserRepository.class);
    private final UserLocationService userLocationService = new GeocalcUserLocationService(repository, cityLocations());
    private static final Users ALL = new Users(ANCELL_GARNSWORTHY, HUGIBERT_DORE, TERRY_STOWGILL);


    @Test
    public void findUsersLivingInOrCurrentlyNearLondon() {
        Users usersLivingInSheffield = new Users(TERRY_STOWGILL);
        when(repository.findAllLivingIn("London")).thenReturn(usersLivingInSheffield);
        when(repository.findAll()).thenReturn(ALL);

        Users actualUsers = userLocationService.findUsersLivingOrNear("London", 40);
        assertThat(actualUsers).isEqualTo(new Users(TERRY_STOWGILL, ANCELL_GARNSWORTHY));
    }

    @Test
    public void failToFindUsersInUnknownCity() {
        when(repository.findAll()).thenReturn(ALL);

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> userLocationService.findUsersLivingOrNear("Sheffield", 10));
    }

    private Map<String, Point> cityLocations() {
        return Map.of("London", Point.at(Coordinate.fromDegrees(51.507222), Coordinate.fromDegrees(-0.1275)));
    }
}
