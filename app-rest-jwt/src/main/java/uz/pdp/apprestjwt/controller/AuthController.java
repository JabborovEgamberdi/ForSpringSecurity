package uz.pdp.apprestjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.apprestjwt.payload.LoginDTO;
import uz.pdp.apprestjwt.security.JwtProvider;
import uz.pdp.apprestjwt.services.MyAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    MyAuthService myAuthService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

//    @PostMapping("/login")
//    public HttpEntity<?> loginToSystem(@RequestBody LoginDTO loginDTO) {
//
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
//            String token = jwtProvider.generateToken(loginDTO.getUsername());
//            System.out.println("token = " + token);
//            return ResponseEntity.ok(token);
//        } catch (BadCredentialsException exception) {
//            return ResponseEntity.status(401).body("Login yoki parol xato!");
//        }
//
//    }

//  with password encoder
    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails = myAuthService.loadUserByUsername(loginDTO.getUsername());
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword());
//        boolean existUser = userDetails.getPassword().equals(loginDTO.getPassword());
        if (matches) {
            String token = jwtProvider.generateToken(loginDTO.getUsername());
            System.out.println("token = " + token);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Login yoki parol xato!");
    }
}

