package uk.co.datumedge.bpdts.repository;

import uk.co.datumedge.bpdts.model.Users;

public interface UserRepository {
    Users findAllLivingIn(String city);
    Users findAll();
}
