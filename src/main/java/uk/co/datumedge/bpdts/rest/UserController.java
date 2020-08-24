package uk.co.datumedge.bpdts.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.datumedge.bpdts.model.User;

import java.util.Collection;

import static java.util.Collections.emptyList;

@RestController
public class UserController {
    @GetMapping("/users/live-or-currently-near/{city}")
    public Collection<User> findLivingOrCurrentlyNear(@PathVariable String city, @RequestParam("within-miles") int withinMiles) {
        return emptyList();
    }
}
