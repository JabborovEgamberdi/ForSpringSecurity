package uz.pdp.apprestjwt.services;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    List<User> userList = new ArrayList<>(
            Arrays
                    .asList(
                            new User("pdp", "pdpUz", new ArrayList<>()),
                            new User("ecma", "ecmaUz", new ArrayList<>()),
                            new User("aif", "aifUz", new ArrayList<>())
                    ));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        throw new UsernameNotFoundException("user not found");
    }
}
