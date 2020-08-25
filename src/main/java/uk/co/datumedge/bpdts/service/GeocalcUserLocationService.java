package uk.co.datumedge.bpdts.service;

import uk.co.datumedge.bpdts.model.Users;

public class GeocalcUserLocationService implements UserLocationService {
    @Override
    public Users getUsersLivingOrNear(String city, int withinMiles) {
        return new Users();
    }
}
