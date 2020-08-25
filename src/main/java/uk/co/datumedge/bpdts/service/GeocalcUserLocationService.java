package uk.co.datumedge.bpdts.service;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import uk.co.datumedge.bpdts.model.User;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.grum.geocalc.EarthCalc.harvesineDistance;

public class GeocalcUserLocationService implements UserLocationService {
    private final UserRepository repository;
    private Map<String, Point> cityLocations;

    public GeocalcUserLocationService(UserRepository repository, Map<String, Point> cityLocations) {
        this.repository = repository;
        this.cityLocations = cityLocations;
    }

    @Override
    public Users findUsersLivingOrNear(String city, int withinMiles) {
        double withinMetres = toMetres(withinMiles);

        List<User> currentlyNearCity = repository.findAll().getUsers().stream()
                .filter(distanceFrom(locationOf(city), withinMetres))
                .collect(Collectors.toList());

        Users livingInCity = repository.findAllLivingIn(city);

        return Users.mergeById(livingInCity, new Users(currentlyNearCity));
    }

    private double toMetres(int miles) {
        return miles * 1609.344; // https://en.wikipedia.org/wiki/Mile
    }

    private Predicate<? super User> distanceFrom(Point target, double metresApart) {
       return (User user) -> harvesineDistance(currentLocationOf(user), target) < metresApart;
    }

    private Point currentLocationOf(User user) {
        return Point.at(Coordinate.fromDegrees(user.getLatitude()), Coordinate.fromDegrees(user.getLongitude()));
    }

    private Point locationOf(String city) {
        if (cityLocations.containsKey(city)) {
            return cityLocations.get(city);
        } else {
            throw new ValidationException("Location of city '" + city + "' is unknown");
        }
    }
}
