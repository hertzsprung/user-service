package uk.co.datumedge.bpdts.service;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import uk.co.datumedge.bpdts.model.User;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Users livingInCity = repository.findAllLivingIn(city);

        List<User> currentlyNearCity = repository.findAll().getUsers().stream()
                .filter(distanceFrom(locationOf(city), withinMetres))
                .collect(Collectors.toList());

        return Users.mergeById(livingInCity, new Users(currentlyNearCity));
    }

    private double toMetres(int miles) {
        return miles * 1609.344; // https://en.wikipedia.org/wiki/Mile
    }

    private Predicate<? super User> distanceFrom(Point target, double metresApart) {
       return (User user) -> EarthCalc.harvesineDistance(currentLocationOf(user), target) < metresApart;
    }

    private Point currentLocationOf(User user) {
        return Point.at(Coordinate.fromDegrees(user.getLatitude()), Coordinate.fromDegrees(user.getLongitude()));
    }

    private Point locationOf(String city) {
        return cityLocations.get(city);
    }
}
