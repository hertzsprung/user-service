package uk.co.datumedge.bpdts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Users {
    private Collection<User> users;

    public static Users mergeById(Users a, Users b) {
        Users users = new Users();
        users.users.addAll(a.users);
        users.users.addAll(b.users);
        return users;
    }

    @JsonCreator
    public Users(User... users) {
        this.users = new ArrayList<>(List.of(users));
    }

    @JsonValue
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users1 = (Users) o;
        return Objects.equals(users, users1.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "Users{" +
                users +
                '}';
    }
}
