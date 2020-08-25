package uk.co.datumedge.bpdts.service;

import com.grum.geocalc.Point;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;

import java.util.Map;

public class GeocalcUserLocationService implements UserLocationService {
    private final UserRepository repository;
    private Map<String, Point> cityLocations;

    public GeocalcUserLocationService(UserRepository repository, Map<String, Point> cityLocations) {
        this.repository = repository;
        this.cityLocations = cityLocations;
    }

    @Override
    public Users findUsersLivingOrNear(String city, int withinMiles) {
        Users livingInCity = repository.findUsersLivingIn(city);
        Point location = cityLocations.get(city);
        Users currentlyNearCity = repository.findUsersCurrentlyNear(city, location.latitude, location.longitude, withinMiles);
        return Users.mergeById(livingInCity, currentlyNearCity);
    }
}
