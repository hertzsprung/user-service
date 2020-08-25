package uk.co.datumedge.bpdts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toCollection;

public class Users {
    private Collection<User> users;

    public static Users mergeById(Users a, Users b) {
        return new Users(Stream.concat(a.users.stream(), b.users.stream())
                .filter(distinctByKey(User::getId))
                .collect(Collectors.toList()));
    }

    // https://stackoverflow.com/a/27872852/150884
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @JsonCreator
    public Users(User... users) {
        this.users = List.of(users);
    }

    public Users(Collection<User> users) {
        this.users = users;
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
