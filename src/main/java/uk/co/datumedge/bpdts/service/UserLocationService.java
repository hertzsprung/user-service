package uk.co.datumedge.bpdts.service;

import uk.co.datumedge.bpdts.model.Users;

public interface UserLocationService {
    Users getUsersLivingOrNear(String city, int withinMiles);
}
