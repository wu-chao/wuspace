package com.github.wuchao.webproject.java.java8;

import com.github.wuchao.webproject.domain.User;
import org.junit.Test;

import java.util.Optional;

public class OptionalTests {

    @Test
    public void testMap() {
        Optional<User> userOptional1 = Optional.empty();
        System.out.println("----------------------" + getUpperCaseName(userOptional1));

        Optional<User> userOptional2 = Optional.of(new User());
        System.out.println("----------------------" + getUpperCaseName(userOptional2));

        User user = new User();
        user.setUsername("姓名aaaaaaaaaaaaaa");
        Optional<User> userOptional3 = Optional.of(user);
        System.out.println("----------------------" + getUpperCaseName(userOptional3));
    }

    public String getUpperCaseName(Optional<User> userOptional) {
        return userOptional.map(user -> user.getUsername())
                .map(name -> name.toUpperCase())
                .orElse("empty");
    }
}
