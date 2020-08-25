package uk.co.datumedge.bpdts.repository;

import uk.co.datumedge.bpdts.model.Users;

public interface UserRepository {
    Users findUsersLivingIn(String city);
    Users findUsersCurrentlyNear(String city, double latitude, double longitude, int withinMiles);
}
