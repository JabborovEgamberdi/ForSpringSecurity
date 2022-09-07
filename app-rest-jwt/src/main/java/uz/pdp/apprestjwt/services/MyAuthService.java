package uz.pdp.apprestjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> userList = new ArrayList<>(
                Arrays
                        .asList(
                                new User("pdp", passwordEncoder.encode("pdpUz"), new ArrayList<>()),
                                new User("ecma", passwordEncoder.encode("ecmaUz"), new ArrayList<>()),
                                new User("aif", passwordEncoder.encode("aifUz"), new ArrayList<>())
                        ));

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        throw new UsernameNotFoundException("user not found");
    }
}
