package uk.co.datumedge.bpdts.service;

import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.repository.UserRepository;

public class GeocalcUserLocationService implements UserLocationService {
    private final UserRepository repository;

    public GeocalcUserLocationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Users findUsersLivingOrNear(String city, int withinMiles) {
        return repository.findUsersLivingIn(city);
    }
}
