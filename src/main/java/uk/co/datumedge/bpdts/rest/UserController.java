package uk.co.datumedge.bpdts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.datumedge.bpdts.model.Users;
import uk.co.datumedge.bpdts.service.UserLocationService;

@RestController
public class UserController {
    @Autowired
    private UserLocationService userLocationService;

    @GetMapping("/users/living-or-currently-near/{city}")
    public Users findLivingOrCurrentlyNear(@PathVariable String city, @RequestParam("within-miles") int withinMiles) {
        return userLocationService.getUsersLivingOrNear(city, withinMiles);
    }
}
